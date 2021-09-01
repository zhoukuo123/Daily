package com.zk.user.service;

import com.zk.user.pojo.Users;
import com.zk.user.pojo.bo.UserBO;
import org.springframework.web.bind.annotation.*;

/**
 * @author CoderZk
 */
@RequestMapping("user-api")
public interface UserService {
    /**
     * 判断用户名是否存在
     */
    @GetMapping("user/exists")
    boolean queryUsernameIsExist(@RequestParam("username") String username);

    /**
     * 创建用户
     */
    @PostMapping("user")
    Users createUser(@RequestBody UserBO userBO);

    /**
     * 检索用户名和密码是否匹配, 用于登录
     */
    @GetMapping("verify")
    Users queryUserForLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password);
}
