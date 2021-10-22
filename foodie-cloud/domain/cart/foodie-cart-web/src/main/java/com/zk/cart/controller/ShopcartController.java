package com.zk.cart.controller;

import com.zk.cart.service.CartService;
import com.zk.controller.BaseController;
import com.zk.pojo.JSONResult;
import com.zk.pojo.ShopcartBO;
import com.zk.utils.JsonUtils;
import com.zk.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CoderZk
 */
@Api(value = "购物车接口controller", tags = {"购物车接口相关的api"})
@RestController
@RequestMapping("/shopcart")
public class ShopcartController extends BaseController {

    @Resource
    private RedisOperator redisOperator;

    @Autowired
    private CartService cartService;

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(
            @RequestParam String userId,
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("");
        }

        System.out.println(shopcartBO);

        cartService.addItemToCart(userId, shopcartBO);

        return JSONResult.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public JSONResult del(
            @RequestParam String userId,
            @RequestParam String itemSepcId,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSepcId)) {
            return JSONResult.errorMsg("参数不能为空");
        }

        cartService.removeItemFromCart(userId, itemSepcId);

        return JSONResult.ok();
    }

    @PostMapping("clearCart")
    public JSONResult clear(@RequestParam String userId) {
        cartService.clearCart(userId);
        return JSONResult.ok();
    }

    //  1) 加减号 - 添加, 减少商品数量
    //         +1 -1 -1 = 0  => -1 -1 +1 = 1 (问题: 如何保证前端请求顺序执行, 在前端处理比较方便)
}
