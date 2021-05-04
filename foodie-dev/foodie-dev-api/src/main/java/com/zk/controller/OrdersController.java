package com.zk.controller;

import com.zk.enums.PayMethod;
import com.zk.pojo.bo.SubmitOrderBO;
import com.zk.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CoderZk
 */
@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public JSONResult create(@RequestBody SubmitOrderBO submitOrderBO) {

        if (!submitOrderBO.getPayMethod().equals(PayMethod.WEIXIN.type) &&
                !submitOrderBO.getPayMethod().equals(PayMethod.ALIPAY.type)) {
            return JSONResult.errorMsg("支付方式不支持!");
        }

        System.out.println(submitOrderBO);

        // 1. 创建订单

        // 2. 创建订单以后, 移除购物车中已结算(已提交)的商品

        // 3. 向支付中心发送当前订单, 用于保存支付中心的订单数据

        return JSONResult.ok();
    }
}
