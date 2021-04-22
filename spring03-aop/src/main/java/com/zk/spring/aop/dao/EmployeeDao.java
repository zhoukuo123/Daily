package com.zk.spring.aop.dao;

import org.springframework.stereotype.Repository;

/**
 * @author CoderZk
 */
@Repository
public class EmployeeDao {
    public void insert() {
        System.out.println("新增员工数据");
    }
}
