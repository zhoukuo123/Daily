package com.zk.service;

import com.zk.pojo.Items;
import com.zk.pojo.ItemsImg;
import com.zk.pojo.ItemsParam;
import com.zk.pojo.ItemsSpec;
import com.zk.pojo.vo.CommentLevelCountsVO;
import com.zk.utils.PagedGridResult;

import java.util.List;

/**
 * @author CoderZk
 */
public interface ItemService {
    /**
     * 根据商品id查询详情
     *
     * @param itemId
     * @return
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     *
     * @param itemId
     * @return
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格列表
     *
     * @param itemId
     * @return
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     *
     * @param itemId
     * @return
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品id查询商品的评价等级数量
     *
     * @param itemId
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id查询商品评价(分页)
     *
     * @param itemId
     * @param level
     * @return
     */
    PagedGridResult queryPagedComments(String itemId, Integer level,
                                       Integer page, Integer pageSize);
}
