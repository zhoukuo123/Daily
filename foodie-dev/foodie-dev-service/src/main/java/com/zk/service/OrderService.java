package com.zk.service;

import com.zk.pojo.OrderStatus;
import com.zk.pojo.bo.ShopcartBO;
import com.zk.pojo.bo.SubmitOrderBO;
import com.zk.pojo.vo.OrderVO;

import java.util.List;

/**
 * @author CoderZk
 */
public interface OrderService {
    /**
     * 用于创建订单相关信息
     *
     * @param submitOrderBO
     */
    OrderVO createOrder(List<ShopcartBO> shopcartList, SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param orderStatus
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     *
     * @param orderId
     * @return
     */
    OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 关闭超时未支付订单
     */
    void closeOrder();
}
