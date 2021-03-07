package com.zk.oa.utils;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.function.Function;

public class MybatisUtilsTestor {
    @Test
    public void testcase1() {
        String string = (String) MybatisUtils.executeQuery(sqlSession -> {
            String str = sqlSession.selectOne("test.sample");
            return str;
        });
        System.out.println(string);
    }
}
