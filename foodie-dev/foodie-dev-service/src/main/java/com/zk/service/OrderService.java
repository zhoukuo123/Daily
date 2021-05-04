package com.zk.service;

import com.zk.pojo.bo.SubmitOrderBO;
import com.zk.pojo.vo.OrderVO;

/**
 * @author CoderZk
 */
public interface OrderService {
    /**
     * 用于创建订单相关信息
     *
     * @param submitOrderBO
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param orderStatus
     */
    void updateOrderStatus(String orderId, Integer orderStatus);
}
