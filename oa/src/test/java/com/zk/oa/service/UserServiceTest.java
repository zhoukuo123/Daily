package com.zk.oa.service;

import com.zk.oa.entity.Node;
import com.zk.oa.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService = new UserService();
    @Test
    public void checkLogin1() {
        userService.checkLogin("uu", "1234");
    }

    @Test
    public void checkLogin2() {
        userService.checkLogin("m8", "1234");
    }

    @Test
    public void checkLogin3() {
        userService.checkLogin("m8", "test");
    }

    @Test
    public void selectNodeByUserId() {
        List<Node> nodes = userService.selectNodeByUserId(2L);
        System.out.println(nodes);
    }
}