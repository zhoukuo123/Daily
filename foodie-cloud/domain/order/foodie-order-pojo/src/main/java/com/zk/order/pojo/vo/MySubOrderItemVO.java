package com.zk.order.pojo.vo;

/**
 * @author CoderZk
 * <p>
 * 用户中心, 我的订单列表嵌套商品VO
 */
public class MySubOrderItemVO {

    private String itemId;
    private String itemName;
    private String itemImg;
    private String itemSpecId;
    private String itemSpecName;
    private Integer price;
    private Integer buyCounts;

    public String getItemId() {
        return itemId;
    }

    public String getItemSpecId() {
        return itemSpecId;
    }

    public void setItemSpecId(String itemSpecId) {
        this.itemSpecId = itemSpecId;
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

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemSpecName() {
        return itemSpecName;
    }

    public void setItemSpecName(String itemSpecName) {
        this.itemSpecName = itemSpecName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getBuyCounts() {
        return buyCounts;
    }

    public void setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
    }
}
