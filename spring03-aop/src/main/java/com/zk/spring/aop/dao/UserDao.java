package com.zk.spring.aop.dao;

import org.springframework.stereotype.Repository;

/**
 * @author CoderZk
 */
@Repository
public class UserDao {
    public void insert() {
        System.out.println("新增用户数据");
    }
}
