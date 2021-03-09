package com.zk.mybatis.dao;

import com.zk.mybatis.dto.GoodsDTO;
import com.zk.mybatis.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsDAO {
    @Select("select * from t_goods where current_price between #{min} and #{max} order by current_price limit 0, #{limt}")
    List<Goods> selectByPriceRange(@Param("min") Float min, @Param("max") Float max, @Param("limt") Integer limt);

    @Insert("insert into t_goods(title, sub_title, original_cost, current_price, discount, is_free_delivery, category_id) value(#{title}, #{subTitle}, #{originalCost}, #{currentPrice}, #{discount}, #{isFreeDelivery}, #{categoryId})")
    // <selectKey>
    @SelectKey(statement = "select last_insert_id()", before = false, keyProperty = "goodsId", resultType = Integer.class)
    int insert(Goods goods);

    @Select("select * from t_goods")
    // <resultMap>
    @Results({
            // <id>
            @Result(column = "goods_id", property = "goodsId", id = true),
            // <result>
            @Result(column = "title", property = "title"),
            @Result(column = "current_price", property = "currentPrice")
    })
    List<GoodsDTO> selectAll();
}
