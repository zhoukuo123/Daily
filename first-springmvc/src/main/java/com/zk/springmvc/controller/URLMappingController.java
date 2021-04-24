package com.zk.springmvc.controller;

import com.zk.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author CoderZk
 */
@Controller
@RequestMapping("/um")
public class URLMappingController {
    @GetMapping("/g")
    @ResponseBody
    public String getMapping(@RequestParam("manager_name") String managerName) {
        return "get method";
    }

    @PostMapping("/p")
    @ResponseBody
    public String postMapping(String username, Long password) {
        return "post method";
    }

    @PostMapping("/p1")
    @ResponseBody
    public String postMapping1(User user) {
        System.out.println(user.getUsername() + user.getPassword());
        return "post method";
    }
}


