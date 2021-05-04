package com.zk.service;

import com.zk.pojo.bo.SubmitOrderBO;

/**
 * @author CoderZk
 */
public interface OrderService {
    /**
     * 用于创建订单相关信息
     *
     * @param submitOrderBO
     */
    void createOrder(SubmitOrderBO submitOrderBO);
}
