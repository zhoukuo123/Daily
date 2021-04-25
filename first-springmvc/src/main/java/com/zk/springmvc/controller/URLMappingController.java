package com.zk.springmvc.controller;

import com.zk.springmvc.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/view")
    public ModelAndView showView(Integer userId) {
//        ModelAndView mav = new ModelAndView("redirect:/view.jsp");
        ModelAndView mav = new ModelAndView();
        // 代表相对路径, 相对于 @RequestMapping("/um")
        mav.setViewName("view.jsp");
        User user = new User();
        if (userId == 1) {
            user.setUsername("lily");
        } else if (userId == 2) {
            user.setUsername("smith");
        }
        mav.addObject("u", user);
        return mav;
    }

    // String 与 ModelMap实现类似 ModelAndView 的功能
    @GetMapping("/xxx")
    public String showView1(Integer userId, ModelMap modelMap) {
        String view = "/um/view.jsp";
        User user = new User();
        if (userId == 1) {
            user.setUsername("lily");
        } else if (userId == 2) {
            user.setUsername("smith");
        }
        modelMap.addAttribute("u", user);
        return view;
    }
}


