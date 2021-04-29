package com.zk.service;

import com.zk.pojo.Category;
import com.zk.pojo.vo.CategoryVO;
import com.zk.pojo.vo.NewItemsVO;

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

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     *
     * @param rootCatId
     * @return
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
