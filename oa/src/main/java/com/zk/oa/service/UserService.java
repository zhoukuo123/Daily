package com.zk.oa.service;

import com.zk.oa.dao.UserDao;
import com.zk.oa.entity.User;
import com.zk.oa.service.exception.BusinessException;

/**
 * 用户业务逻辑类
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public User checkLogin(String username, String password) {
        User user = userDao.selectByUsername(username);
        if (user == null) {
            // 抛出用户不存在异常
            throw new BusinessException("L001", "用户名不存在");
        }
        if (!password.equals(user.getPassword())) {
            throw new BusinessException("L002", "密码错误");
        }
        return user;
    }
}
