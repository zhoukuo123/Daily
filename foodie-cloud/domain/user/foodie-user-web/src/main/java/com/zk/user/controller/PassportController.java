package com.zk.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zk.auth.service.AuthService;
import com.zk.auth.service.pojo.Account;
import com.zk.auth.service.pojo.AuthCode;
import com.zk.auth.service.pojo.AuthResponse;
import com.zk.controller.BaseController;
import com.zk.pojo.JSONResult;
import com.zk.pojo.ShopcartBO;
import com.zk.user.UserApplicationProperties;
import com.zk.user.pojo.Users;
import com.zk.user.pojo.bo.UserBO;
import com.zk.user.pojo.vo.UsersVO;
import com.zk.user.service.UserService;
import com.zk.user.stream.ForceLogoutTopic;
import com.zk.utils.CookieUtils;
import com.zk.utils.JsonUtils;
import com.zk.utils.MD5Utils;
import com.zk.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * @author CoderZk
 */
@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("/passport")
@Slf4j
public class PassportController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private RedisOperator redisOperator;

    @Autowired
    private UserApplicationProperties userApplicationProperties;

    @Autowired
    private AuthService authService;

    @Autowired
    private ForceLogoutTopic producer;

    private static final String AUTH_HEADER = "Authorization";
    private static final String REFRESH_TOKEN_HEADER = "refresh-token";
    private static final String UID_HEADER = "user-id";

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            return JSONResult.errorMsg("用户名不能为空");
        }
        return userService.queryUsernameIsExist(username) ? JSONResult.errorMsg("用户名已经存在") : JSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public JSONResult register(@RequestBody UserBO userBO,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        if (userApplicationProperties.isDisableRegistration()) {
            log.info("user registration request is blocked - {}", userBO.getUsername());
            return JSONResult.errorMsg("当前注册用户过多, 请稍后再试");
        }


        // 校验
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // 0. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        // 1. 查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return JSONResult.errorMsg("用户名已经存在");
        }
        // 2. 密码长度不能少于6位
        if (password.length() < 6) {
            return JSONResult.errorMsg("密码长度不能少于6");
        }

        // 3. 判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return JSONResult.errorMsg("两次密码输入不一致");
        }

        // 4. 实现注册
        Users userResult = userService.createUser(userBO);

//        userResult = setNullProperty(userResult);

        // 实现用户的redis会话, 生成用户token, 存入redis会话
        UsersVO usersVO = convertUsersVO(userResult);

        // 把用户信息放入cookie中, 前端获取后显示为登录时的状态, 并且刷新也不会丢失登录状态
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(usersVO), true);

        // 同步购物车数据
        syncShopcartData(userResult.getId(), request, response);

        return JSONResult.ok();
    }

    private UsersVO convertUsersVO(Users user) {
        // 实现用户的redis会话
        String uniqueToken = UUID.randomUUID().toString().trim();
        redisOperator.set(REDIS_USER_TOKEN + ":" + user.getId(), uniqueToken);

        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(user, usersVO);
        usersVO.setUserUniqueToken(uniqueToken);
        return usersVO;
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    @HystrixCommand(
            commandKey = "loginFail", // 全局唯一的标识服务, 默认函数名称
            groupKey = "password", // 全局服务分组, 用于组织仪表盘, 统计信息. 默认: 类名
            fallbackMethod = "loginFail", // 同一个类里, public private 都可以
//            ignoreExceptions = {IllegalArgumentException.class} // 在列表中的exception, 不会触发降级
            // 线程有关的属性
            // 线程组, 多个服务可以公用一个线程组
            threadPoolKey = "threadPoolA",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"), // 核心线程数
                    // size > 0, LinkedBlockingQueue -> 请求等待队列
                    // 默认-1, SynchronousQueue -> 不存储元素的阻塞队列(建议读源码, 学CAS应用)
                    @HystrixProperty(name = "maxQueueSize", value = "40"),
                    // 在maxQueueSize=-1的时候无效, 队列没有达到maxQueueSize依然拒绝
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    // (线程池)统计窗口持续时间
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1024"),
                    // (线程池)窗口内桶子的数量
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "18"),
            }
//            ,
//            commandProperties = {
//                    // TODO: 熔断降级相关属性, 也可以放在这里
//            }
    )
    public JSONResult login(@RequestBody UserBO userBO,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        // 0. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }

        // 1. 实现登录
        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));

        if (userResult == null) {
            return JSONResult.errorMsg("用户名或密码不正确");
        }

        AuthResponse token = authService.tokennize(userResult.getId());
        if (!AuthCode.SUCCESS.getCode().equals(token.getCode())) {
            log.error("Token error - uid={}", userResult.getId());
            return JSONResult.errorMsg("Token error");
        }

        // 将token添加到header当中
        addAuth2Header(response, token.getAccount());

        // 信息脱敏
//        userResult = setNullProperty(userResult);

        // 实现用户的redis会话, 生成用户token, 存入redis会话
        UsersVO usersVO = convertUsersVO(userResult);

        // 把用户信息放入cookie中, 前端获取后显示为登录时的状态, 并且刷新也不会丢失登录状态
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(usersVO), true);

        // 同步购物车数据
        syncShopcartData(userResult.getId(), request, response);

        return JSONResult.ok(userResult);
    }

    private JSONResult loginFail(@RequestBody UserBO userBO,
                                HttpServletRequest request,
                                HttpServletResponse response,
                                Throwable throwable) throws Exception {
        return JSONResult.errorMsg("验证码输错了(模仿12306)");
    }

    /**
     * 注册登陆成功后, 同步cookie和redis中的购物车数据
     */
    // TODO 放到购物车模块, 作为一个接口, 提供给 User 微服务调用
    private void syncShopcartData(String userId, HttpServletRequest request, HttpServletResponse response) {
        /**
         * 1. redis中无数据, 如果cookie中的购物车为空, 那么这个时候不做任何处理
         *                  如果cookie中的购物车不为空, 此时直接放入redis中
         * 2. redis中有数据, 如果cookie中的购物车为空, 那么直接把redis的购物车覆盖本地cookie
         *                  如果cookie中的购物车不为空, 如果cookie中的某个商品在redis中存在, 则以cookie为主, 删除redis中的, 把cookie中的商品直接覆盖redis中(参考京东)
         * 3. 同步到redis中去了以后, 覆盖本地cookie购物车的数据, 保证本地购物车的数据是同步最新的
         */
        // 从redis中获取购物车
        String shopcartJsonRedis = redisOperator.get(FOODIE_SHOPCART + ":" + userId);
        // 从cookie中获取购物车
        String shopcartStrCookie = CookieUtils.getCookieValue(request, FOODIE_SHOPCART, true);
        if (StringUtils.isBlank(shopcartJsonRedis)) {
            // redis为空, cookie不为空, 直接把cookie中的数据放入redis
            if (StringUtils.isNotBlank(shopcartStrCookie)) {
                redisOperator.set(FOODIE_SHOPCART + ":" + userId, shopcartStrCookie);
            }
        } else {
            // redis不为空, cookie不为空, 合并cookie和redis中购物车的商品数据(同一商品则覆盖redis)
            if (StringUtils.isNotBlank(shopcartStrCookie)) {
                /**
                 * 1. 已经存在的, 把cookie中对应的数量, 覆盖redis(参考jd)
                 * 2. 该项商品标记为待删除, 统一放入一个待删除的list
                 * 3. 从cookie中清理所有的待删除list
                 * 4. 合并redis和cookie中的数据
                 * 5. 更新到redis和cookie中
                 */
                List<ShopcartBO> shopcartListRedis = JsonUtils.jsonToList(shopcartJsonRedis, ShopcartBO.class);
                List<ShopcartBO> shopcartListCookie = JsonUtils.jsonToList(shopcartStrCookie, ShopcartBO.class);

                // 定义一个待删除list
                List<ShopcartBO> pendingDeleteList = new ArrayList<>();

                for (ShopcartBO redisShopcart : shopcartListRedis) {
                    String redisSpecId = redisShopcart.getSpecId();

                    for (ShopcartBO cookieShopcart : shopcartListCookie) {
                        String cookieSpecId = cookieShopcart.getSpecId();

                        if (redisSpecId.equals(cookieSpecId)) {
                            // 覆盖购买数量, 不累加, 参考jd
                            redisShopcart.setBuyCounts(cookieShopcart.getBuyCounts());
                            // 把cookieShopcart放入待删除列表, 用于最后的删除与合并
                            pendingDeleteList.add(cookieShopcart);
                        }

                    }
                }

                // 从现有cookie中删除对应的覆盖过的商品数据
                shopcartListCookie.removeAll(pendingDeleteList);

                // 合并两个list
                shopcartListRedis.addAll(shopcartListCookie);

                // 更新到redis和cookie
                redisOperator.set(FOODIE_SHOPCART + ":" + userId, JsonUtils.objectToJson(shopcartListRedis));
                CookieUtils.setCookie(request, response, FOODIE_SHOPCART,
                        JsonUtils.objectToJson(shopcartListRedis), true);
            } else {
                // redis不为空, cookie为空, 直接把redis的购物车覆盖本地cookie
                CookieUtils.setCookie(request, response, FOODIE_SHOPCART,
                        shopcartJsonRedis, true);
            }
        }
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        userResult.setRealname(null);

        return userResult;
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public JSONResult logout(@RequestParam String userId,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        Account account = Account.builder()
                .token(request.getHeader(AUTH_HEADER))
                .refreshToken(request.getHeader(REFRESH_TOKEN_HEADER))
                .userId(userId)
                .build();
        AuthResponse auth = authService.delete(account);
        if (!AuthCode.SUCCESS.getCode().equals(auth.getCode())) {
            log.error("Token error - uid={}", userId);
            return JSONResult.errorMsg("Token error");
        }

        // 清除用户相关信息的cookie
        CookieUtils.deleteCookie(request, response, "user");

        // 用户退出登录, 需要清空购物车
        CookieUtils.deleteCookie(request, response, FOODIE_SHOPCART);

        // 用户退出登录, 清除redis中user的会话信息
        redisOperator.del(REDIS_USER_TOKEN + ":" + userId);

        return JSONResult.ok();
    }

    // TODO 修改前端js代码
    // 在前端页面里拿到Authorization, refresh-token和user-id
    // 前端每次请求服务, 都把这几个参数带上
    private void addAuth2Header(HttpServletResponse response, Account token) {
        response.setHeader(AUTH_HEADER, token.getToken());
        response.setHeader(REFRESH_TOKEN_HEADER, token.getRefreshToken());
        response.setHeader(UID_HEADER, token.getUserId());

        // 让前端感知到, 过期时间一天, 这样可以在临近过期的时候refresh token
        Calendar expTime = Calendar.getInstance();
        expTime.add(Calendar.DAY_OF_MONTH, 1);
        response.setHeader("token-exp-time", expTime.getTimeInMillis() + "");
    }

    // FIXME 将这个接口从网关层移除, 不对外暴露
    @ApiOperation(value = "用户强制退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/forceLogout")
    public JSONResult forceLogout(@RequestParam String userIds) {
        if (StringUtils.isNotBlank(userIds)) {
            for (String uid : userIds.split(",")) {
                log.info("send logout message, uid={}", uid);
                producer.output().send(MessageBuilder.withPayload(uid).build());
            }
        }

        return JSONResult.ok();
    }

}
