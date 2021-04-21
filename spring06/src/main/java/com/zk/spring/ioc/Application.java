package com.zk.spring.ioc;

import com.zk.spring.ioc.context.ApplicationContext;
import com.zk.spring.ioc.context.ClassPathXmlApplicationContext;
import com.zk.spring.ioc.entity.Apple;

/**
 * @author CoderZk
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Apple sweetApple = (Apple) context.getBean("sweetApple");
        System.out.println(sweetApple);
    }
}
