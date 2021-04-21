package com.zk.oa.dao;

import com.zk.oa.entity.Node;
import com.zk.oa.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RbacDao {
    public List<Node> selectNodeByUserId(Long userId) {
        return (List) MybatisUtils.executeQuery((SqlSession sqlSession) -> {
            return sqlSession.selectList("rbacmapper.selectNodeByUserId", userId);
        });
    }
}
