package com.zk.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * MyBatisUtils工具类, 创建全局唯一的SqlSessionFactory对象
 */
public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
            // 初始化错误时, 通过抛出异常ExceptionInInitializerError通知调用者
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 创建一个新的SqlSession对象
     * @return SqlSession对象
     */
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 释放一个有效的SqlSession对象
     */
    public static void closeSession(SqlSession sqlSession) {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
}
