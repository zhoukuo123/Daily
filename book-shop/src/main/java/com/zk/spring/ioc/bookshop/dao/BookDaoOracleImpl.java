package com.zk.spring.ioc.bookshop.dao;

public class BookDaoOracleImpl implements BookDao {
    @Override
    public void insert() {
        System.out.println("Oracle Book表插入一条数据");
    }
}
