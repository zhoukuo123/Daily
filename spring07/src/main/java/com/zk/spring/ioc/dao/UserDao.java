package com.zk.spring.ioc.dao;

import org.springframework.stereotype.Repository;

/**
 * @author CoderZk
 * <p>
 * 组件类型注解默认beanId为类名首字母小写
 * beanId = userDao
 */
//@Repository("udao")
@Repository
public class UserDao implements IUserDao {

}
