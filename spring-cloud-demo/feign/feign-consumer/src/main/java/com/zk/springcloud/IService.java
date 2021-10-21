package com.zk.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CoderZk
 */
@FeignClient("eureka-client")
public interface IService {
    @GetMapping("/sayHi")
    String sayHi();
}
