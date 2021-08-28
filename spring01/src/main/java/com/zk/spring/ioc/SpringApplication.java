package com.zk.spring.ioc;

import com.zk.spring.ioc.entity.Apple;
import com.zk.spring.ioc.entity.Child;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplication {
    public static void main(String[] args) {
        // 创建Spring IoC容器, 并根据配置文件在容器中实例化对象
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Apple sweetApple = context.getBean("sweetApple", Apple.class);
        Apple sweetApple2 = context.getBean("sweetApple2", Apple.class);
        System.out.println(sweetApple.getTitle());
        System.out.println(sweetApple2.getTitle());
//        Child lily = context.getBean("lily", Child.class);
//        lily.eat();

        // 获取容器内所有beanId数组
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
            System.out.println("类型:" + context.getBean(beanName).getClass().getName());
            System.out.println("内容:" + context.getBean(beanName));
        }

    }
}
