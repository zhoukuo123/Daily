package com.zk.spring.ioc;

import com.zk.spring.ioc.entity.Apple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        String[] configLocations = new String[]{"classpath:applicationContext.xml", "classpath:applicationContext-1.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        Apple apple1 = context.getBean("apple1", Apple.class);
        System.out.println(apple1.getTitle());
        Apple apple = context.getBean("com.zk.spring.ioc.entity.Apple", Apple.class);
        System.out.println(apple.getTitle());
    }
}
