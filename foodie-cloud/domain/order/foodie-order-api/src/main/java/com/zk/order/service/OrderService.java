package com.zk.order.service;

import com.zk.order.pojo.OrderStatus;
import com.zk.order.pojo.bo.PlaceOrderBO;
import com.zk.order.pojo.bo.SubmitOrderBO;
import com.zk.order.pojo.vo.OrderVO;
import com.zk.pojo.ShopcartBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CoderZk
 */
@FeignClient("foodie-order-service")
@RequestMapping("order-api")
public interface OrderService {
    /**
     * 用于创建订单相关信息
     *
     * @param orderBO
     */
    @PostMapping("placeOrder")
    OrderVO createOrder(@RequestBody PlaceOrderBO orderBO);

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param orderStatus
     */
    @PostMapping("updateStatus")
    void updateOrderStatus(@RequestParam("orderId") String orderId,
                           @RequestParam("orderStatus") Integer orderStatus);

    /**
     * 查询订单状态
     *
     * @param orderId
     * @return
     */
    @GetMapping("orderStatus")
    OrderStatus queryOrderStatusInfo(@RequestParam("orderId") String orderId);

    /**
     * 关闭超时未支付订单
     */
    @PostMapping("closePendingOrders")
    void closeOrder();
}
