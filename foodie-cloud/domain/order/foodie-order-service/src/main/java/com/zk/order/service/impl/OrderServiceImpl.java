package com.zk.order.service.impl;

import com.zk.enums.OrderStatusEnum;
import com.zk.enums.YesOrNo;
import com.zk.item.pojo.Items;
import com.zk.item.pojo.ItemsSpec;
import com.zk.item.service.ItemService;
import com.zk.order.mapper.OrderItemsMapper;
import com.zk.order.mapper.OrderStatusMapper;
import com.zk.order.mapper.OrdersMapper;
import com.zk.order.pojo.OrderItems;
import com.zk.order.pojo.OrderStatus;
import com.zk.order.pojo.Orders;
import com.zk.order.pojo.bo.PlaceOrderBO;
import com.zk.order.pojo.bo.SubmitOrderBO;
import com.zk.order.pojo.vo.MerchantOrdersVO;
import com.zk.order.pojo.vo.OrderVO;
import com.zk.order.service.OrderService;
import com.zk.pojo.ShopcartBO;
import com.zk.user.pojo.UserAddress;
import com.zk.user.service.AddressService;
import com.zk.utils.DateUtil;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CoderZk
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderItemsMapper orderItemsMapper;

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ItemService itemService;

    @Resource
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderVO createOrder(PlaceOrderBO orderBO) {
        List<ShopcartBO> shopcartList = orderBO.getItems();
        SubmitOrderBO submitOrderBO = orderBO.getOrder();

        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        // 包邮费用设置为0
        Integer postAmount = 0;

        String orderId = sid.nextShort();

        UserAddress address = addressService.queryUserAddress(userId, addressId);

        // 1. 新订单数据保存
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);

        newOrder.setReceiverName(address.getReceiver());
        newOrder.setReceiverMobile(address.getMobile());
        newOrder.setReceiverAddress(address.getProvince() + " " + address.getCity() + " "
                + address.getDistrict() + " " + address.getDetail());

//        newOrder.setTotalAmount();
//        newOrder.setRealPayAmount();
        newOrder.setPostAmount(postAmount);

        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);

        newOrder.setIsComment(YesOrNo.NO.type);
        newOrder.setIsDelete(YesOrNo.NO.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());

        // 2. 循环根据itemSpecIds保存订单商品信息表
        String[] itemSpecIdArr = itemSpecIds.split(",");
        Integer totalAmount = 0;    // 商品原价累计
        Integer realPayAmount = 0;  // 优惠后的实际支付价格累计
        List<ShopcartBO> toBeRemovedShopcartList = new ArrayList<>();
        for (String itemSpecId : itemSpecIdArr) {

            // 整合redis后, 商品购买的数量重新从redis的购物车中获取
            ShopcartBO cartItem = getBuyCountsFromShopcart(shopcartList, itemSpecId);
            int buyCounts = cartItem.getBuyCounts();
            toBeRemovedShopcartList.add(cartItem);

            // 2.1 根据规格id, 查询规格的具体信息, 主要获取价格
            ItemsSpec itemsSpec = itemService.queryItemSpecById(itemSpecId);
            totalAmount += itemsSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemsSpec.getPriceDiscount() * buyCounts;

            // 2.2 根据商品id, 获得商品信息以及商品图片
            String itemId = itemsSpec.getItemId();
            Items item = itemService.queryItemById(itemId);
            String imgUrl = itemService.queryItemMainImgById(itemId);

            // 2.3 循环保存子订单数据到数据库
            String subOrderId = sid.nextShort();
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setId(subOrderId);
            subOrderItem.setOrderId(orderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemName(item.getItemName());
            subOrderItem.setItemImg(imgUrl);
            subOrderItem.setBuyCounts(buyCounts);
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemsSpec.getName());
            subOrderItem.setPrice(itemsSpec.getPriceDiscount());
            orderItemsMapper.insert(subOrderItem);

            // 2.4 在用户提交订单以后, 规格表中需要扣除库存
            itemService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        ordersMapper.insert(newOrder);

        // 3. 保存订单状态表
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(waitPayOrderStatus);

        // 4. 构建商户订单, 用于传给支付中心
        MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();
        merchantOrdersVO.setMerchantOrderId(orderId);
        merchantOrdersVO.setMerchantUserId(userId);
        merchantOrdersVO.setAmount(realPayAmount + postAmount);
        merchantOrdersVO.setPayMethod(payMethod);

        // 5. 构建自定义订单vo
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orderId);
        orderVO.setMerchantOrdersVO(merchantOrdersVO);
        orderVO.setToBeRemovedShopcartList(toBeRemovedShopcartList);

        return orderVO;
    }

    /**
     * 从redis中的购物车里获取商品, 目的: buyCounts
     *
     * @param shopcartList
     * @param specId
     * @return
     */
    private ShopcartBO getBuyCountsFromShopcart(List<ShopcartBO> shopcartList, String specId) {
        for (ShopcartBO shopcartBO : shopcartList) {
            if (shopcartBO.getSpecId().equals(specId)) {
                return shopcartBO;
            }
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateOrderStatus(String orderId, Integer orderStatus) {

        OrderStatus paidStatus = new OrderStatus();
        paidStatus.setOrderId(orderId);
        paidStatus.setOrderStatus(orderStatus);
        paidStatus.setPayTime(new Date());

        orderStatusMapper.updateByPrimaryKeySelective(paidStatus);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatus queryOrderStatusInfo(String orderId) {
        return orderStatusMapper.selectByPrimaryKey(orderId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void closeOrder() {
        // 查询所有未付款订单, 判断时间是否超时(1天), 超时则关闭交易
        OrderStatus queryOrder = new OrderStatus();
        queryOrder.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> list = orderStatusMapper.select(queryOrder);
        for (OrderStatus orderStatus : list) {
            // 获得订单创建时间
            Date createdTime = orderStatus.getCreatedTime();
            // 和当前时间进行对比
            int days = DateUtil.daysBetween(createdTime, new Date());
            if (days >= 1) {
                // 超过1天, 关闭订单
                doCloseOrder(orderStatus.getOrderId());
            }
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    void doCloseOrder(String orderId) {
        OrderStatus close = new OrderStatus();
        close.setOrderId(orderId);
        close.setOrderStatus(OrderStatusEnum.CLOSE.type);
        close.setCloseTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(close);
    }
}
