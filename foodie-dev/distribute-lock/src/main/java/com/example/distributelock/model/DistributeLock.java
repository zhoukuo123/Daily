package com.example.distributelock.model;

public class DistributeLock {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column distribute_lock.id
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column distribute_lock.business_code
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    private String businessCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column distribute_lock.business_name
     *
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    private String businessName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column distribute_lock.id
     *
     * @return the value of distribute_lock.id
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column distribute_lock.id
     *
     * @param id the value for distribute_lock.id
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column distribute_lock.business_code
     *
     * @return the value of distribute_lock.business_code
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    public String getBusinessCode() {
        return businessCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column distribute_lock.business_code
     *
     * @param businessCode the value for distribute_lock.business_code
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode == null ? null : businessCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column distribute_lock.business_name
     *
     * @return the value of distribute_lock.business_name
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column distribute_lock.business_name
     *
     * @param businessName the value for distribute_lock.business_name
     * @mbg.generated Tue Aug 13 17:54:34 CST 2019
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }
}