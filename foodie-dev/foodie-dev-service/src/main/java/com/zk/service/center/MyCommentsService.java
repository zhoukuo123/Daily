package com.zk.service.center;

import com.zk.pojo.OrderItems;
import com.zk.pojo.bo.center.OrderItemsCommentBO;
import com.zk.utils.PagedGridResult;

import java.util.List;

/**
 * @author CoderZk
 */
public interface MyCommentsService {
    /**
     * 根据订单id查询关联的商品
     *
     * @param orderId
     * @return
     */
    List<OrderItems> queryPendingComment(String orderId);

    /**
     * 保存用户的评论
     *
     * @param userId
     * @param orderId
     * @param commentList
     */
    void saveComments(String userId, String orderId, List<OrderItemsCommentBO> commentList);

    /**
     * 我的评价查询 分页
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
