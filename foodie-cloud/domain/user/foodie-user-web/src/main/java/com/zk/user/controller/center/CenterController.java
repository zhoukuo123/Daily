package com.zk.user.controller.center;

import com.zk.pojo.JSONResult;
import com.zk.user.pojo.Users;
import com.zk.user.service.center.CenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CoderZk
 */
@Api(value = "center - 用户中心", tags = "用户中心展示的相关接口")
@RestController
@RequestMapping("/center")
public class CenterController {

    @Resource
    private CenterUserService centerUserService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET")
    @GetMapping("/userInfo")
    public JSONResult userInfo(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {

        Users user = centerUserService.queryUserInfo(userId);
        return JSONResult.ok(user);
    }
}
