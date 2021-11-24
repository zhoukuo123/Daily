package com.zk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author CoderZk
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@Slf4j
public class SleuthTraceBMain {

    @LoadBalanced
    @Bean
    public RestTemplate lb() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/traceB")
    public String traceB() {
        log.info("------Trace B");
        return "traceB";
    }


    public static void main(String[] args) {
        SpringApplication.run(SleuthTraceBMain.class, args);
    }
}
