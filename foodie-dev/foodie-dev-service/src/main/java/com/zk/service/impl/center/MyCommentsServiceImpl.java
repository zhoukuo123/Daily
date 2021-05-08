package com.zk.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.zk.enums.YesOrNo;
import com.zk.mapper.ItemsCommentsMapperCustom;
import com.zk.mapper.OrderItemsMapper;
import com.zk.mapper.OrderStatusMapper;
import com.zk.mapper.OrdersMapper;
import com.zk.pojo.OrderItems;
import com.zk.pojo.OrderStatus;
import com.zk.pojo.Orders;
import com.zk.pojo.bo.center.OrderItemsCommentBO;
import com.zk.pojo.vo.MyCommentVO;
import com.zk.service.center.MyCommentsService;
import com.zk.utils.PagedGridResult;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private ItemsCommentsMapperCustom itemsCommentsMapperCustom;

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
        itemsCommentsMapperCustom.saveComments(map);

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

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);

        List<MyCommentVO> list = itemsCommentsMapperCustom.queryMyComments(map);

        return setterPagedGrid(list, page);
    }
}
