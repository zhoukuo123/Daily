package com.zk.service;

import com.zk.pojo.UserAddress;
import com.zk.pojo.bo.AddressBO;

import java.util.List;

/**
 * @author CoderZk
 */
public interface AddressService {
    /**
     * 根据用户id查询用户的收货地址列表
     *
     * @param userId
     * @return
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 用户新增地址
     *
     * @param addressBO
     */
    void addNewUserAddress(AddressBO addressBO);

    /**
     * 用户修改地址
     *
     * @param addressBO
     */
    void updateUserAddress(AddressBO addressBO);

    /**
     * 修改默认地址
     *
     * @param userId
     * @param addressId
     */
    void deleteUserAddress(String userId, String addressId);

    void updateUserAddressToBeDefault(String userId, String addressId);
}
