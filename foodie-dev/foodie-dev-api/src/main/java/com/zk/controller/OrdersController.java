package com.zk.controller;

import com.zk.enums.OrderStatusEnum;
import com.zk.enums.PayMethod;
import com.zk.pojo.OrderStatus;
import com.zk.pojo.bo.ShopcartBO;
import com.zk.pojo.bo.SubmitOrderBO;
import com.zk.pojo.vo.MerchantOrdersVO;
import com.zk.pojo.vo.OrderVO;
import com.zk.service.OrderService;
import com.zk.utils.CookieUtils;
import com.zk.utils.JSONResult;
import com.zk.utils.JsonUtils;
import com.zk.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author CoderZk
 */
@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RestController
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    @Resource
    private OrderService orderService;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RedisOperator redisOperator;

    @Autowired
    private RedissonClient redissonClient;

    @ApiOperation(value = "获取订单token", notes = "获取订单token", httpMethod = "POST")
    @PostMapping("/getOrderToken")
    public JSONResult getOrderToken(HttpSession session) {
        String token = UUID.randomUUID().toString();
        redisOperator.set("ORDER_TOEKN_" + session.getId(), token, 600);
        return JSONResult.ok(token);
    }

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public JSONResult create(@RequestBody SubmitOrderBO submitOrderBO,
                             HttpServletRequest request, HttpServletResponse response) {


        String orderTokenKey = "ORDER_TOEKN_" + request.getSession().getId();
        String lockKey = "LOCK_KEY_" + request.getSession().getId();

        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(5, TimeUnit.SECONDS);

        try {
            String orderToken = redisOperator.get(orderTokenKey);

            if (StringUtils.isBlank(orderToken)) {
                throw new RuntimeException("orderToken 不存在");
            }

            boolean correctToken = orderToken.equals(submitOrderBO.getToken());
            if (!correctToken) {
                throw new RuntimeException("orderToken 不正确");
            }

            redisOperator.del(orderTokenKey);
        } finally {
            try {
                lock.unlock();
            } catch (Exception e) {

            }
        }


        if (!submitOrderBO.getPayMethod().equals(PayMethod.WEIXIN.type) &&
                !submitOrderBO.getPayMethod().equals(PayMethod.ALIPAY.type)) {
            return JSONResult.errorMsg("支付方式不支持!");
        }

//        System.out.println(submitOrderBO);

        String shopcartJson = redisOperator.get(FOODIE_SHOPCART + ":" + submitOrderBO.getUserId());
        if (StringUtils.isBlank(shopcartJson)) {
            return JSONResult.errorMsg("购物车数据不正确");
        }

        List<ShopcartBO> shopcartList = JsonUtils.jsonToList(shopcartJson, ShopcartBO.class);

        // 1. 创建订单
        OrderVO orderVO = orderService.createOrder(shopcartList, submitOrderBO);
        String orderId = orderVO.getOrderId();

        // 2. 创建订单以后, 移除购物车中已结算(已提交)的商品
        // 清理覆盖现有的redis中的购物车数据
        shopcartList.removeAll(orderVO.getToBeRemovedShopcartList());
        // 整合redis之后, 完善购物车中的已结算商品清除, 并且同步到前端的cookie
        CookieUtils.setCookie(request, response, FOODIE_SHOPCART, JsonUtils.objectToJson(shopcartList), true);
        redisOperator.set(FOODIE_SHOPCART + ":" + submitOrderBO.getUserId(), JsonUtils.objectToJson(shopcartList));

        // 3. 向支付中心发送当前订单, 用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        // 设置支付中心回调天天吃货的url
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        // 为了方便测试购买, 所以所有的支付金额都统一改为1分钱
        merchantOrdersVO.setAmount(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId", "imooc");
        headers.add("password", "imooc");

        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, headers);

        ResponseEntity<JSONResult> responseEntity =
                restTemplate.postForEntity(paymentUrl, entity, JSONResult.class);

        JSONResult paymentResult = responseEntity.getBody();
        if (paymentResult.getStatus() != 200) {
            return JSONResult.errorMsg("支付中心订单创建失败, 请联系管理员");
        }

        return JSONResult.ok(orderId);
    }

    /**
     * 支付中心回调通知订单已支付, 设置订单状态为已支付
     *
     * @param merchantOrderId
     * @return
     */
    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @PostMapping("/getPaidOrderInfo")
    public JSONResult getPaidOrderInfo(@RequestParam String orderId) {
        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);
        return JSONResult.ok(orderStatus);
    }
}
