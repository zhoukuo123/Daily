package com.zk.pojo.vo;

/**
 * @author CoderZk
 * <p>
 * 用于展示商品搜索列表结果的VO
 */
public class SearchItemsVO {
    private String itemId;
    private String itemName;
    private Integer sellCounts;
    private String imgUrl;
    // 数据库中的price都是以分为单位, 在前端会/100来转换
    // 在绝大多数场景下, 在后端涉及到金额都是以分为单位
    private Integer price;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getSellCounts() {
        return sellCounts;
    }

    public void setSellCounts(Integer sellCounts) {
        this.sellCounts = sellCounts;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
