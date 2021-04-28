package com.zk.service;

import com.zk.pojo.Stu;
import com.zk.pojo.Users;
import com.zk.pojo.bo.UserBO;

/**
 * @author CoderZk
 */
public interface UserService {
    /**
     * 判断用户名是否存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     */
    Users createUser(UserBO userBO);
}
