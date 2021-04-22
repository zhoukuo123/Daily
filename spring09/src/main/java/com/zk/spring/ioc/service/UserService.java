package com.zk.spring.ioc.service;

import com.zk.spring.ioc.dao.UserDao;

/**
 * @author CoderZk
 */
public class UserService {
    private UserDao userDao;

    public void createUser() {
        System.out.println("调用创建用户业务代码");
        userDao.insert();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
