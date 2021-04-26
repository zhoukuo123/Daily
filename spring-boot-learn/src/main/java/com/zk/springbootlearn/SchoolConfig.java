package com.zk.springbootlearn;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author CoderZk
 * School配置类
 */
@Component
@ConfigurationProperties(prefix = "school")
public class SchoolConfig {
    private Integer grade;

    private Integer classnum;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getClassnum() {
        return classnum;
    }

    public void setClassnum(Integer classnum) {
        this.classnum = classnum;
    }
}
