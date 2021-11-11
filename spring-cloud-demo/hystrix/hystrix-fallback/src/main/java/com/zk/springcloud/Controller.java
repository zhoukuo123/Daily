package com.zk.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.zk.springcloud.hystrix.RequestCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author CoderZk
 */
@RestController
public class Controller {

    @Autowired
    private MyService myService;

    @Autowired
    private RequestCacheService requestCacheService;

    @GetMapping("/fallback")
    public String fallback() {
        return myService.error();
    }

    @GetMapping("/timeout")
    public String timeout(@RequestParam int timeout) {
        return myService.retry(timeout);
    }

    @GetMapping("/timeout2")
    @HystrixCommand(fallbackMethod = "timeoutFallback",
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String timeout2(@RequestParam int timeout) {
        return myService.retry(timeout);
    }

    public String timeoutFallback(@RequestParam int timeout) {
        return "success";
    }

    @GetMapping("/cache")
    public Friend cache(@RequestParam String name) {
        /* @Cleanup */ HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            Friend friend = requestCacheService.requestCache(name);
//        name += "rose";
            friend = requestCacheService.requestCache(name);
            return friend;
        } finally {
            context.close();
        }
    }

}
