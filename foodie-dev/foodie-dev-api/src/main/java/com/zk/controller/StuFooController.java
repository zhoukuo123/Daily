package com.zk.controller;

import com.zk.pojo.Stu;
import com.zk.service.StuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author CoderZk
 */
@ApiIgnore
@RestController
public class StuFooController {

    @Resource
    private StuService stuService;

    @GetMapping("/getStu")
    public Stu getStu(int id) {
        return stuService.getStuInfo(id);
    }

    @PostMapping("/saveStu")
    public String saveStu() {
        stuService.saveStu();
        return "OK";
    }

    @PostMapping("/updateStu")
    public String updateStu(int id) {
        stuService.updateStu(id);
        return "OK";
    }

    @PostMapping("/deleteStu")
    public String deleteStu(int id) {
        stuService.deleteStu(id);
        return "OK";
    }
}
