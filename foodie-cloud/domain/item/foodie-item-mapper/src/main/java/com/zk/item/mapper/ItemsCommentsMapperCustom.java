package com.zk.item.mapper;

import com.zk.item.pojo.ItemsComments;
import com.zk.item.pojo.vo.MyCommentVO;
import com.zk.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {
    void saveComments(Map<String, Object> map);

    List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);
}