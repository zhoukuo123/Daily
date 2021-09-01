package com.zk.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author CoderZk
 */
@SpringBootApplication

// 扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "com.zk.item.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"com.zk", "org.n3r.idworker"})
@EnableDiscoveryClient
// TODO add feign 注解
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}
