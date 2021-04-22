package com.zk.spring.ioc.service;

import com.zk.spring.ioc.dao.IUserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author CoderZk
 */
@Service
@Scope("prototype") // 设置单例/多例, XML中bean scope完全相同
public class UserService {
    @Resource
    private IUserDao userDao;

    @Value("${metaData}") // 读取config.properties的metaData属性值
    private String metaData;

    @PostConstruct // XML中bean init-method完全相同
    public void init() {
        System.out.println("初始化UserService对象, metaData=" + metaData);
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
