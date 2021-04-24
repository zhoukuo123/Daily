package com.zk.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CoderZk
 */
@Controller
public class TestController {
    @GetMapping("/t")
    @ResponseBody // 直接向响应输出字符串数据, 不跳转页面
    public String test() {
        return "success";
    }
}
