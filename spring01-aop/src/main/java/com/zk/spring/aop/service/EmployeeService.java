package com.zk.spring.aop.service;

import com.zk.spring.aop.dao.EmployeeDao;

/**
 * @author CoderZk
 */
public class EmployeeService {
    private EmployeeDao employeeDao;

    public void entry() {
        System.out.println("执行员工入职业务逻辑");
        employeeDao.insert();
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
