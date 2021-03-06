package com.zk.oa.dao;

import com.zk.oa.entity.User;
import com.zk.oa.utils.MybatisUtils;

/**
 * 用户表DAO
 */
public class UserDao {
    public User selectByUsername(String username) {
        return (User) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("usermapper.selectByUsername", username));
    }
}
