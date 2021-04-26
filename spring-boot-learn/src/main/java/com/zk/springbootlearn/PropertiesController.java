package com.zk.springbootlearn;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author CoderZk
 * 演示读取配置的Controller
 */
@RestController
public class PropertiesController {
    Integer grade;
    Integer classnum;

    public String gradeClass() {
        return "年级: " + grade + " 班级: " + classnum;
    }
}
