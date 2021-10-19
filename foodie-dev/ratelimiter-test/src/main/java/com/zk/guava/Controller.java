package com.zk.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author CoderZk
 */
@RestController
@Slf4j
public class Controller {

    private RateLimiter limiter = RateLimiter.create(2.0);
    private RateLimiter limiter2 = RateLimiter.create(10.0);

    // 非阻塞限流
    @GetMapping("/tryAcquire")
    public String tryAcquire(Integer count) {
        if (limiter.tryAcquire(count)) {
            log.info("success, rate is {}", limiter.getRate());
            return "success";
        } else {
            log.info("fail, rate is {}", limiter.getRate());
            return "fail";
        }
    }

    // 限定时间的非阻塞限流
    @GetMapping("/tryAcquireWithTimeout")
    public String tryAcquireWithTimeout(Integer count, Integer timeout) {
        if (limiter2.tryAcquire(count, timeout, TimeUnit.SECONDS)) {
            log.info("success, rate is {}", limiter.getRate());
            return "success";
        } else {
            log.info("fail, rate is {}", limiter.getRate());
            return "fail";
        }
    }

    // 同步阻塞限流
    @GetMapping("/acquire")
    public String acquire() {
        limiter.acquire();
        log.info("success, rate is {}", limiter.getRate());
        return "success";
    }
}
