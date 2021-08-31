package com.zk.item.mapper;

import com.zk.item.pojo.vo.ItemCommentVO;
import com.zk.item.pojo.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);

    // TODO 迁移到foodie-search模块
//    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);
//
//    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> map);

    List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List<String> specIdsList);

    int decreaseItemSpecStock(@Param("specId") String specId, @Param("pendingCounts") Integer pendingCounts);
}