package com.zk.mapper;

import com.zk.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryMapperCustom {
    List<CategoryVO> getSubCatList(Integer rootCatId);
}