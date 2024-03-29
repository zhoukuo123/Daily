package com.zk.item.service;

import com.zk.item.pojo.Items;
import com.zk.item.pojo.ItemsImg;
import com.zk.item.pojo.ItemsParam;
import com.zk.item.pojo.ItemsSpec;
import com.zk.item.pojo.vo.CommentLevelCountsVO;
import com.zk.item.pojo.vo.ShopcartVO;
import com.zk.pojo.PagedGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author CoderZk
 */
@FeignClient("foodie-item-service")
@RequestMapping("/item-api")
public interface ItemService {
    /**
     * 根据商品id查询详情
     *
     * @param itemId
     * @return
     */
    @GetMapping("item")
    Items queryItemById(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品图片列表
     *
     * @param itemId
     * @return
     */
    @GetMapping("itemImages")
    List<ItemsImg> queryItemImgList(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品规格列表
     *
     * @param itemId
     * @return
     */
    @GetMapping("itemSpecs")
    List<ItemsSpec> queryItemSpecList(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品参数
     *
     * @param itemId
     * @return
     */
    @GetMapping("itemParam")
    ItemsParam queryItemParam(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品的评价等级数量
     *
     * @param itemId
     */
    @GetMapping("countComments")
    CommentLevelCountsVO queryCommentCounts(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品评价(分页)
     *
     * @param itemId
     * @param level
     * @return
     */
    @GetMapping("pagedComments")
    PagedGridResult queryPagedComments(@RequestParam("itemId") String itemId,
                                       @RequestParam(value = "level", required = false) Integer level,
                                       @RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "pageSize", required = false)  Integer pageSize);

//    /**
//     * 搜索商品列表
//     *
//     * @param keywords
//     * @param sort
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    PagedGridResult searchItems(String keywords, String sort,
//                                Integer page, Integer pageSize);
//
//    /**
//     * 根据分类id搜索商品列表
//     *
//     * @param catId
//     * @param sort
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    PagedGridResult searchItems(Integer catId, String sort,
//                                Integer page, Integer pageSize);

    /**
     * 根据规格ids查询最新的购物车中商品数据(用于刷新渲染购物车中的商品数据)
     *
     * @param specIds
     * @return
     */
    @GetMapping("getCartBySpecIds")
    List<ShopcartVO> queryItemsBySpecIds(@RequestParam("specIds") String specIds);

    /**
     * 根据商品规格id获取规格对象的具体信息
     *
     * @param specId
     * @return
     */
    @GetMapping("singleItemSpec")
    ItemsSpec queryItemSpecById(@RequestParam("specId") String specId);

    /**
     * 根据商品id获得商品图片主图url
     *
     * @param itemId
     * @return
     */
    @GetMapping("primaryImage")
    String queryItemMainImgById(@RequestParam("itemId") String itemId);

    /**
     * 减少库存
     *
     * @param specId
     * @param buyCounts
     */
    @PostMapping("decreaseStock")
    void decreaseItemSpecStock(@RequestParam("itemId") String specId,
                               @RequestParam("buyCounts") Integer buyCounts);
}
