package com.zk.service.center;

import com.zk.pojo.Users;
import com.zk.pojo.bo.center.CenterUserBO;

/**
 * @author CoderZk
 */
public interface CenterUserService {
    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    Users queryUserInfo(String userId);

    /**
     * 修改用户信息
     *
     * @param userId
     * @param centerUserBO
     */
    Users updateUserInfo(String userId, CenterUserBO centerUserBO);
}
