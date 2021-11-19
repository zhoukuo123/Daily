package com.zk.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author CoderZk
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigBusClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigBusClientApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
