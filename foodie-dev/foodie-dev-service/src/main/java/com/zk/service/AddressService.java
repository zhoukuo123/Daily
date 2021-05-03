package com.zk.service;

import com.zk.pojo.UserAddress;

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
}
