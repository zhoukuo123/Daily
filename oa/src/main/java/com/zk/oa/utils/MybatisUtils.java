package com.zk.oa.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    // 利用静态块在初始化类时实例化sqlSessionFactory
    static {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory =  new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 执行SELECT查询SQL
     * @param func 要执行查询语句的代码块
     * @return 查询结果
     */
    public static Object executeQuery(Function<SqlSession, Object> func) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            Object obj = func.apply(sqlSession);
            return obj;
        } finally {
            sqlSession.close();
        }
    }
}
