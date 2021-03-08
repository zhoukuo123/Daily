package com.zk.oa.dao;

import com.zk.oa.entity.Node;
import com.zk.oa.utils.MybatisUtils;

import java.util.List;

public class RbacDao {
    public List<Node> selectNodeByUserId(Long userId) {
        return  (List) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectList("rbacmapper.selectNodeByUserId", userId));
    }
}
