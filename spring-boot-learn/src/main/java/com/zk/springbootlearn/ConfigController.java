package com.zk.springbootlearn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CoderZk
 * 读取配置类
 */
@RestController
public class ConfigController {
    @Resource
    private SchoolConfig schoolConfig;

    @GetMapping("/gradefromconfig")
    public String gradeClass() {
        return "年级: " + schoolConfig.getGrade() + " 班级: " + schoolConfig.getClassnum();
    }
}
