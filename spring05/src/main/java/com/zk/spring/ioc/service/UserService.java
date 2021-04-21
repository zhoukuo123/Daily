package com.zk.spring.ioc.service;

import com.zk.spring.ioc.dao.UserDao;

/**
 * @author CoderZk
 */
public class UserService {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
