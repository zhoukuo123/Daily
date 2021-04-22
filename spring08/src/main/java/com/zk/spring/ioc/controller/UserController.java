package com.zk.spring.ioc.controller;

import com.zk.spring.ioc.service.UserService;

/**
 * @author CoderZk
 */
public class UserController {
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
