package com.zk.mapper;

import com.zk.my.mapper.MyMapper;
import com.zk.pojo.ItemsComments;
import com.zk.pojo.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom extends MyMapper<ItemsComments> {
    void saveComments(Map<String, Object> map);

    List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);
}