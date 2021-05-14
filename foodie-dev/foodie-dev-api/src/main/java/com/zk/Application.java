package com.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author CoderZk
 */
@SpringBootApplication // (exclude = {SecurityAutoConfiguration.class})
// 扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "com.zk.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"com.zk", "org.n3r.idworker"})
@EnableScheduling   // 开启定时任务
//@EnableRedisHttpSession // 开启使用redis作为spring session
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
