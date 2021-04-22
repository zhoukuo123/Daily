package com.zk.spring.ioc.service;

import com.zk.spring.ioc.dao.IUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CoderZk
 */
@Service
public class UserService {
    @Resource
    private IUserDao userDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
