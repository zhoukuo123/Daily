package com.zk.spring.ioc.service;

import com.zk.spring.ioc.dao.EmployeeDao;
import com.zk.spring.ioc.dao.UserDao;

/**
 * @author CoderZk
 */
public class UserService {
    private UserDao userDao;
    private EmployeeDao employeeDao;

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
