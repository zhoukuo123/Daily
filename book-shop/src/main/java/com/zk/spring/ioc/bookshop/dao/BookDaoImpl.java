package com.zk.spring.ioc.bookshop.dao;

public class BookDaoImpl implements BookDao {
    @Override
    public void insert() {
        System.out.println("向MySQL Book表插入一条数据");
    }
}
