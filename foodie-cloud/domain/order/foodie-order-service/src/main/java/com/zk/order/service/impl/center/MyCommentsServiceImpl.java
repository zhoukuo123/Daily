package com.zk.order.service.impl.center;

import com.zk.enums.YesOrNo;
import com.zk.item.service.ItemCommentsService;
import com.zk.order.fallback.itemservice.ItemCommentsFeignClient;
import com.zk.order.mapper.OrderItemsMapper;
import com.zk.order.mapper.OrderStatusMapper;
import com.zk.order.mapper.OrdersMapper;
import com.zk.order.pojo.OrderItems;
import com.zk.order.pojo.OrderStatus;
import com.zk.order.pojo.Orders;
import com.zk.order.pojo.bo.center.OrderItemsCommentBO;
import com.zk.order.service.center.MyCommentsService;
import com.zk.service.BaseService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CoderZk
 */
@Service
public class MyCommentsServiceImpl extends BaseService implements MyCommentsService {

    @Resource
    private OrderItemsMapper orderItemsMapper;

//    @Resource
//    private ItemsCommentsMapperCustom itemsCommentsMapperCustom;

    @Autowired
//    private ItemCommentsService itemCommentsService;
    private ItemCommentsFeignClient itemCommentsService;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Resource
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems query = new OrderItems();
        query.setOrderId(orderId);
        return orderItemsMapper.select(query);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveComments(String userId, String orderId, List<OrderItemsCommentBO> commentList) {

        // 1. 保存评价 items_comments
        for (OrderItemsCommentBO oic : commentList) {
            oic.setCommentId(sid.nextShort());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commentList", commentList);
//        itemsCommentsMapperCustom.saveComments(map);
        itemCommentsService.saveComments(map);

        // 2. 修改订单表改为已评价 orders
        Orders order = new Orders();
        order.setId(orderId);
        order.setIsComment(YesOrNo.YES.type);
        ordersMapper.updateByPrimaryKeySelective(order);

        // 3. 修改订单状态表的留言时间 order_status
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    // TODO 移到了itemCommentService
//    @Transactional(propagation = Propagation.SUPPORTS)
//    @Override
//    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize) {
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("userId", userId);
//
//        PageHelper.startPage(page, pageSize);
//
//        List<MyCommentVO> list = itemsCommentsMapperCustom.queryMyComments(map);
//
//        return setterPagedGrid(list, page);
//    }
}
