package com.zk;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author CoderZk
 * <p>
 * 打包war [4] 添加war包的启动类
 */
public class WarStarterApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指向Application这个SpringBoot启动类
        return builder.sources(Application.class);
    }
}
