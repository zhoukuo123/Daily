package com.zk.mybatis;

import com.zk.mybatis.dao.GoodsDAO;
import com.zk.mybatis.dto.GoodsDTO;
import com.zk.mybatis.entity.Goods;
import com.zk.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyBatisTestor {
    @Test
    public void testSelectByPriceRange() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            GoodsDAO goodsDAO = sqlSession.getMapper(GoodsDAO.class);
            List<Goods> goods = goodsDAO.selectByPriceRange(100f, 500f, 20);
            System.out.println(goods.size());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            Goods goods = new Goods();
            goods.setTitle("测试商品");
            goods.setSubTitle("测试子标题");
            goods.setOriginalCost(200f);
            goods.setCurrentPrice(100f);
            goods.setDiscount(0.5f);
            goods.setIsFreeDelivery(1);
            goods.setCategoryId(43);
            GoodsDAO goodsDAO = sqlSession.getMapper(GoodsDAO.class);
            // insert()方法返回值代表本次成功插入的记录总数
            int num = goodsDAO.insert(goods);
            sqlSession.commit(); // 提交事务数据
            System.out.println(goods.getGoodsId());
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback(); // 回滚事务
            }
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = null;
        try {
            sqlSession = MyBatisUtils.openSession();
            GoodsDAO goodsDAO = sqlSession.getMapper(GoodsDAO.class);
            List<GoodsDTO> goodsDTOList = goodsDAO.selectAll();
            System.out.println(goodsDTOList.size());
        } catch (Exception e) {
            throw e;
        } finally {
            MyBatisUtils.closeSession(sqlSession);
        }
    }
}
