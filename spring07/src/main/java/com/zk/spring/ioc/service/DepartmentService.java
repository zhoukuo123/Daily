package com.zk.spring.ioc.service;

import com.zk.spring.ioc.dao.IUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CoderZk
 */
@Service
public class DepartmentService {
//    @Resource(name = "userOracleDao")
//    private IUserDao udao;

    @Resource
    private IUserDao userOracleDao;
}
