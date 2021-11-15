package com.zk.order;

import com.zk.item.service.ItemCommentsService;
import com.zk.item.service.ItemService;
import com.zk.order.fallback.itemservice.ItemCommentsFeignClient;
import com.zk.user.service.AddressService;
import com.zk.user.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author CoderZk
 */
@SpringBootApplication
// 扫描 mybatis 通用 mapper 所在的包
@MapperScan(basePackages = "com.zk.order.mapper")
// 扫描所有包以及相关组件包
@ComponentScan(basePackages = {"com.zk", "org.n3r.idworker"})
@EnableDiscoveryClient
@EnableFeignClients(
        clients = {
                ItemCommentsFeignClient.class,
//                ItemCommentsService.class,
                UserService.class,
                AddressService.class,
                ItemService.class
        }
//        basePackages = {
//                "com.zk.item.service",
//                "com.zk.user.service",
//                "com.zk.order.fallback.itemservice"
//        }
)
@EnableScheduling
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
