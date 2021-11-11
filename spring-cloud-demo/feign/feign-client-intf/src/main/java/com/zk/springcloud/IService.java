package com.zk.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CoderZk
 */
@FeignClient(value = "feign-client", primary = false)
public interface IService {

    @GetMapping("/sayHi")
    String sayHi();

    @PostMapping("/sayHi")
    Friend sayHiPost(@RequestBody Friend friend);

    @GetMapping("/retry")
    String retry(@RequestParam(name = "timeout") int timeout);

    @GetMapping("/error")
    String error();
}
