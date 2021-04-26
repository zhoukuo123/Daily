package com.zk.springbootlearn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CoderZk
 * 演示读取配置的Controller
 */
@RestController
public class PropertiesController {
    @Value("${school.grade}")
    private Integer grade;

    @Value("${school.classnum}")
    private Integer classnum;

    @GetMapping("/gradeclass")
    public String gradeClass() {
        return "年级: " + grade + " 班级: " + classnum;
    }
}
