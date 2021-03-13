package com.zk.spring.ioc;

import com.zk.spring.ioc.entity.Apple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        Apple apple1 = context.getBean("apple1", Apple.class);
        System.out.println(apple1.getTitle());
    }
}
