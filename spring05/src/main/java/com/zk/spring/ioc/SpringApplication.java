package com.zk.spring.ioc;

import com.zk.spring.ioc.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author CoderZk
 */
public class SpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        UserDao userDao1 = context.getBean("userDao", UserDao.class);
        UserDao userDao2 = context.getBean("userDao", UserDao.class);
        UserDao userDao3 = context.getBean("userDao", UserDao.class);
    }
}
