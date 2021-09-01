package com.zk.order.service.center;

import com.zk.order.pojo.Orders;
import com.zk.order.pojo.vo.OrderStatusCountsVO;
import com.zk.pojo.JSONResult;
import com.zk.pojo.PagedGridResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author CoderZk
 */
@RequestMapping("myorder-api")
public interface MyOrdersService {
    /**
     * 查询我的订单列表
     *
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("order/query")
    PagedGridResult queryMyOrders(@RequestParam("userId") String userId,
                                  @RequestParam("orderStatus") Integer orderStatus,
                                  @RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize);

    /**
     * 订单状态 --> 商家发货
     *
     * @param orderId
     */
    @PostMapping("order/delivered")
    void updateDeliverOrderStatus(@RequestParam("orderId") String orderId);

    /**
     * 查询我的订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("order/details")
    Orders queryMyOrder(@RequestParam("userId") String userId,
                        @RequestParam("orderId") String orderId);

    /**
     * 更新订单状态 -> 确认收货
     *
     * @param orderId
     * @return
     */
    @PostMapping("order/received")
    boolean updateReceiveOrderStatus(@RequestParam("orderId") String orderId);

    /**
     * 删除订单(逻辑删除)
     *
     * @param userId
     * @param orderId
     * @return
     */
    @DeleteMapping("order")
    boolean deleteOrder(@RequestParam("userId") String userId,
                        @RequestParam("orderId") String orderId);

    /**
     * 查询用户订单状态的数量
     *
     * @param userId
     */
    @GetMapping("order/counts")
    OrderStatusCountsVO getOrderStatusCounts(@RequestParam("userId") String userId);

    /**
     * 获得分页的订单动向
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("order/trend")
    PagedGridResult getOrdersTrend(@RequestParam("userId") String userId,
                                   @RequestParam("page") Integer page,
                                   @RequestParam("pageSize") Integer pageSize);

    /**
     * 用于验证用户和订单是否有关联关系, 避免非法用户调用
     *
     * @return
     */
    @GetMapping("checkUserOrder")
    JSONResult checkUserOrder(@RequestParam("userId") String userId,
                              @RequestParam("orderId") String orderId);
}
