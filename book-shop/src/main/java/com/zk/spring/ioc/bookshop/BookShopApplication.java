package com.zk.spring.ioc.bookshop;

import com.zk.spring.ioc.bookshop.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookShopApplication {
    public static void main(String[] args) {
//        String[] configLocations = new String[]{"classpath:applicationContext-service.xml", "classpath:applicationContext-dao.xml"};
//        ApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-*.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.purchase();
    }
}
