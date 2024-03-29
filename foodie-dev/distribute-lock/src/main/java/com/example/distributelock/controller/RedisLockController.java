package com.example.distributelock.controller;

import com.example.distributelock.lock.RedisLock;
import com.example.distributelock.lock.ZkLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RedisLockController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("redisLock")
    public String redisLock() {

        log.info("我进入了方法！");

        // try-with-resources 自动调用语句管理的对象的 close 方法处理unlock操作
        try (RedisLock redisLock = new RedisLock(redisTemplate, "redisKey", 30)) {
            if (redisLock.getLock()) {
                log.info("我进入了锁！！");
                Thread.sleep(15000);
            } else {
                log.info("当前锁已被占用, 拿不到分布式锁");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("方法执行完成");
        return "方法执行完成";
    }

    @RequestMapping("zkLock")
    public String zkLock() {

        log.info("我进入了方法！");

        try (ZkLock zkLock = new ZkLock("localhost:2181", "order")) {
            if (zkLock.getLock()) {
                log.info("我进入了锁！！");
                Thread.sleep(15000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("方法执行完成");
        return "方法执行完成";
    }
}
