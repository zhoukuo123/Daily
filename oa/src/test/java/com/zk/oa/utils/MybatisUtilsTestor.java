package com.zk.oa.utils;

import org.junit.Test;

public class MybatisUtilsTestor {
    @Test
    public void testcase1() {
        String string = (String) MybatisUtils.executeQuery(sqlSession -> {
            String str = sqlSession.selectOne("test.sample");
            return str;
        });
        System.out.println(string);
    }

    @Test
    public void testcase2() {
        String string = (String) MybatisUtils.executeQuery(sqlSession ->
                sqlSession.selectOne("test.sample")
        );
        System.out.println(string);
    }
}
