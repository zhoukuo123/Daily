package com.test;

import com.zk.Application;
import com.zk.service.TestTransService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author CoderZk
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class TransTest {

    @Resource
    private TestTransService testTransService;

//    @Test
    public void myTest() {
        testTransService.testPropagationTrans();
    }
}
