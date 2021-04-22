package com.zk.spring.ioc;

import com.zk.spring.ioc.controller.UserController;
import com.zk.spring.ioc.dao.EmployeeDao;
import com.zk.spring.ioc.dao.UserDao;
import com.zk.spring.ioc.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author CoderZk
 */
@Configuration
@ComponentScan(basePackages = "com.zk")
public class Config {
    @Bean // Java Config利用方法创建对象, 将方法返回的对象放入IoC容器, beanId = 方法名
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    // 先按name尝试注入, name不存在则按类型注入
    public UserService userService(UserDao userDao, EmployeeDao employeeDao) {
        UserService userService = new UserService();
        userService.setUserDao(userDao);
        userService.setEmployeeDao(employeeDao);
        return userService;
    }

    @Bean
    public UserController userController(UserService userService) {
        UserController userController = new UserController();
        userController.setUserService(userService);
        return userController;
    }
}
