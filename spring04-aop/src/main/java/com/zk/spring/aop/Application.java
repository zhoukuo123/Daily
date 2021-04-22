package com.zk.spring.aop;

import com.zk.spring.aop.service.UserService;
import com.zk.spring.aop.service.UserServiceImpl;
import com.zk.spring.aop.service.UserServiceProxy;
import com.zk.spring.aop.service.UserServiceProxy1;

/**
 * @author CoderZk
 */
public class Application {
    public static void main(String[] args) {
//        直接面向委托类(业务逻辑实现类)
        UserService userService1 = new UserServiceImpl();
        userService1.createUser();

//        面向代理类
        UserService userService2 = new UserServiceProxy(new UserServiceImpl());
        userService2.createUser();

//        面向二房东(嵌套代理类)
        UserService userService3 = new UserServiceProxy1(new UserServiceProxy(new UserServiceImpl()));
        userService3.createUser();
    }
}
