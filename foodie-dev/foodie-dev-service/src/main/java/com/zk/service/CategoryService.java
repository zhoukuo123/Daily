package com.zk.service;

import com.zk.pojo.Category;
import com.zk.pojo.vo.CategoryVO;

import java.util.List;

/**
 * @author CoderZk
 */
public interface CategoryService {
    /**
     * 查询所有一级分类
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类id查询子分类信息
     *
     * @param rootCatId
     * @return
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);
}
