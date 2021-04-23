package com.zk.spring.aop;

import com.zk.spring.aop.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author CoderZk
 */
public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
        userService.createUser();
    }
}
