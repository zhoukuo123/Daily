package com.zk.controller;

import com.zk.pojo.Stu;
import com.zk.service.StuService;
import com.zk.service.UserService;
import com.zk.utils.JSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author CoderZk
 */
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Resource
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            return JSONResult.errorMsg("用户名不能为空");
        }
        return userService.queryUsernameIsExist(username) ? JSONResult.errorMsg("用户名已经存在") : JSONResult.ok();
    }

}
