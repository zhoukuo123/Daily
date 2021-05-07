package com.zk.controller;

import org.springframework.stereotype.Controller;

import java.io.File;

/**
 * @author CoderZk
 */
@Controller
public class BaseController {

    public static final String FOODIE_SHOPCART = "shopcart";

    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    // 支付中心的调用地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";


    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    //                       |-> 回调通知的url
    // 支付中心是部署到云服务器上的, 回调通知时, 无法访问到本地的URL, 需要内网穿透
    String payReturnUrl = "http://localhost:8088/orders/notifyMerchantOrderPaid";

    // 用户上传头像的位置
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "home" +
            File.separator + "linux" +
            File.separator + "images" +
            File.separator + "foodie" +
            File.separator + "faces";

}
