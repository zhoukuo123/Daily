package com.example.distributedemo.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.id
     *
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.product_name
     *
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    private String productName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.price
     *
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.count
     *
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    private Integer count;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.product_desc
     *
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    private String productDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.create_time
     *
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.create_user
     *
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.update_time
     *
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.update_user
     *
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    private String updateUser;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.id
     *
     * @return the value of product.id
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.id
     *
     * @param id the value for product.id
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.product_name
     *
     * @return the value of product.product_name
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.product_name
     *
     * @param productName the value for product.product_name
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.price
     *
     * @return the value of product.price
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.price
     *
     * @param price the value for product.price
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.count
     *
     * @return the value of product.count
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.count
     *
     * @param count the value for product.count
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.product_desc
     *
     * @return the value of product.product_desc
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.product_desc
     *
     * @param productDesc the value for product.product_desc
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc == null ? null : productDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.create_time
     *
     * @return the value of product.create_time
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.create_time
     *
     * @param createTime the value for product.create_time
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.create_user
     *
     * @return the value of product.create_user
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.create_user
     *
     * @param createUser the value for product.create_user
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.update_time
     *
     * @return the value of product.update_time
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.update_time
     *
     * @param updateTime the value for product.update_time
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.update_user
     *
     * @return the value of product.update_user
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.update_user
     *
     * @param updateUser the value for product.update_user
     * @mbg.generated Fri Aug 09 11:20:17 CST 2019
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}