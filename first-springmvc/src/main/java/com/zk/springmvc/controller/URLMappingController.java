package com.zk.springmvc.controller;

import com.zk.springmvc.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author CoderZk
 */
@Controller
@RequestMapping("/um")
public class URLMappingController {
    @GetMapping("/g")
    @ResponseBody
    public String getMapping(@RequestParam("manager_name") String managerName) {
        System.out.println("managerName:" + managerName);
        return "get method";
    }

    @PostMapping("/p")
    @ResponseBody
    public String postMapping(String username, Long password, Date createTime) {
        System.out.println(username + password);
        return "post method";
    }

    @PostMapping("/p1")
    @ResponseBody
    public String postMapping1(User user, @DateTimeFormat(pattern = "yyyy-MM-dd") Date createTime) {
        System.out.println(user.getUsername() + user.getPassword());
        return "post method 中文测试";
    }
}


