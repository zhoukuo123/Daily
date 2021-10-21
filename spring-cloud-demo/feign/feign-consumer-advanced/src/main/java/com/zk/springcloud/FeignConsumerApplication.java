package com.zk.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author CoderZk
 */
@SpringBootApplication
@EnableDiscoveryClient
// 因为这个微服务要调用其他微服务的接口, 所以要加这个注解, 配置Feign的扫包路径, 这里默认路径是@FeignClient注解接口的包路径为扫包路径
@EnableFeignClients
public class FeignConsumerApplication {

//    @FeignClient(value = "feign-client", primary = true)
//    public interface MyService extends IService {
//
//    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(FeignConsumerApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
