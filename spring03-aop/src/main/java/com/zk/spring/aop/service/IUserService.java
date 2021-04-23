package com.zk.spring.aop.service;

/**
 * @author CoderZk
 */
public interface IUserService {
    void createUser();

    String generateRandomPassword(String type, Integer length);
}
