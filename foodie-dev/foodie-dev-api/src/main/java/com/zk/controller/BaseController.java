package com.zk.controller;

import com.zk.pojo.Orders;
import com.zk.pojo.Users;
import com.zk.pojo.UsersVO;
import com.zk.service.center.MyOrdersService;
import com.zk.utils.JSONResult;
import com.zk.utils.RedisOperator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

/**
 * @author CoderZk
 */
@Controller
public class BaseController {

    @Resource
    private RedisOperator redisOperator;

    public static final String FOODIE_SHOPCART = "shopcart";

    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    public static final String REDIS_USER_TOKEN = "redis_user_token";

    // 支付中心的调用地址
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";


    // 微信支付成功 -> 支付中心 -> 天天吃货平台
    //                       |-> 回调通知的url
    // 支付中心是部署到云服务器上的, 回调通知时, 无法访问到本地的URL, 需要内网穿透
    String payReturnUrl = "http://101.200.229.14:8088/foodie-dev-api/orders/notifyMerchantOrderPaid";

    // 用户上传头像的位置
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "home" +
            File.separator + "linux" +
            File.separator + "images" +
            File.separator + "foodie" +
            File.separator + "faces";


    @Resource
    public MyOrdersService myOrdersService;

    /**
     * 用于验证用户和订单是否有关联关系, 避免非法用户调用
     *
     * @return
     */
    public JSONResult checkUserOrder(String userId, String orderId) {
        Orders order = myOrdersService.queryMyOrder(userId, orderId);
        if (order == null) {
            return JSONResult.errorMsg("订单不存在");
        }
        return JSONResult.ok(order);
    }

    public UsersVO convertUsersVO(Users user) {
        // 实现用户的redis会话
        String uniqueToken = UUID.randomUUID().toString().trim();
        redisOperator.set(REDIS_USER_TOKEN + ":" + user.getId(), uniqueToken);

        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(user, usersVO);
        usersVO.setUserUniqueToken(uniqueToken);
        return usersVO;
    }
}
