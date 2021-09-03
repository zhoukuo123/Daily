package com.zk.cart.service;

import com.zk.pojo.ShopcartBO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CoderZk
 */
@RequestMapping("cart-api")
public interface CartService {

    @PostMapping("addItem")
    boolean addItemToCart(@RequestParam("userId") String userId,
                          @RequestBody ShopcartBO shopcartBO);

    @PostMapping("removeItem")
    boolean removeItemFromCart(@RequestParam("userId") String userId,
                               @RequestParam("itemSepcId") String itemSepcId);

    @PostMapping("clearCart")
    boolean clearCart(@RequestParam("userId") String userId);
}
