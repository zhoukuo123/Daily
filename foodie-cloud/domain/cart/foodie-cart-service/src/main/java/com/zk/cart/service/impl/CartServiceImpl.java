package com.zk.cart.service.impl;

import com.zk.cart.service.CartService;
import com.zk.pojo.JSONResult;
import com.zk.pojo.ShopcartBO;
import com.zk.utils.JsonUtils;
import com.zk.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.zk.controller.BaseController.FOODIE_SHOPCART;

/**
 * @author CoderZk
 */
@RestController
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisOperator redisOperator;

    @Override
    public boolean addItemToCart(String userId, ShopcartBO shopcartBO) {
        // 前端用户在登录的情况下, 添加商品到购物车, 会同时在后端同步购物车到redis缓存
        // 需要判断当前购物车中包含已经存在的商品, 如果存在则累加购买数量
        String shopcartJson = redisOperator.get(FOODIE_SHOPCART + ":" + userId);
        List<ShopcartBO> shopcartList;
        if (StringUtils.isNotBlank(shopcartJson)) {
            // redis中已经有购物车了
            shopcartList = JsonUtils.jsonToList(shopcartJson, ShopcartBO.class);
            // 判断购物车中是否存在已有商品, 如果有的话counts累加
            boolean isHaving = false;
            for (ShopcartBO sc : shopcartList) {
                String tmpSpecId = sc.getSpecId();
                if (tmpSpecId.equalsIgnoreCase(shopcartBO.getSpecId())) {
                    sc.setBuyCounts(sc.getBuyCounts() + shopcartBO.getBuyCounts());
                    isHaving = true;
                }
            }
            if (!isHaving) {
                shopcartList.add(shopcartBO);
            }
        } else {
            // redis中没有购物车
            shopcartList = new ArrayList<>();
            // 直接添加到购物车中
            shopcartList.add(shopcartBO);
        }

        // 覆盖现有redis的购物车
        redisOperator.set(FOODIE_SHOPCART + ":" + userId, JsonUtils.objectToJson(shopcartList));
        return true;
    }

    @Override
    public boolean removeItemFromCart(String userId, String itemSepcId) {
        // 用户在页面删除购物车中的商品数据, 如果此时用户已经登录, 则需要同步删除redis购物车中的商品
        String shopcartJson = redisOperator.get(FOODIE_SHOPCART + ":" + userId);
        if (StringUtils.isNotBlank(shopcartJson)) {
            // redis中已经有购物车了
            List<ShopcartBO> shopcartList = JsonUtils.jsonToList(shopcartJson, ShopcartBO.class);
            // 判断购物车中是否存在已有商品, 如果有的话则删除
            for (ShopcartBO sc : shopcartList) {
                String tmpSpecId = sc.getSpecId();
                if (tmpSpecId.equals(itemSepcId)) {
                    shopcartList.remove(sc);
                    break;
                }
            }
            // 覆盖现有redis中的购物车
            redisOperator.set(FOODIE_SHOPCART + ":" + userId, JsonUtils.objectToJson(shopcartList));
        }
        return true;
    }

    @Override
    public boolean clearCart(String userId) {
        redisOperator.del(FOODIE_SHOPCART + ":" + userId);
        return true;
    }
}
