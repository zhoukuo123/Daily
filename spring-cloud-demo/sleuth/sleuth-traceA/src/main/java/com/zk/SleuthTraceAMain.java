package com.zk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
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
public class SleuthTraceAMain {

    @LoadBalanced
    @Bean
    public RestTemplate lb() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/traceA")
    public String traceA() {
        log.info("------Trace A");

        return restTemplate.getForEntity("http://sleuth-traceB/traceB", String.class).getBody();
    }


    public static void main(String[] args) {
        SpringApplication.run(SleuthTraceAMain.class, args);
    }
}
