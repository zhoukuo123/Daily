package com.zk.service.impl;

import com.zk.service.StuService;
import com.zk.service.TestTransService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author CoderZk
 */
@Service
public class TestTransServiceImpl implements TestTransService {
    @Resource
    private StuService stuService;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagationTrans() {
        stuService.saveParent();

        stuService.saveChildren();

//        int a = 1 / 0;
    }
}
