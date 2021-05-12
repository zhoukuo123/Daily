package com.zk.controller;

import com.zk.utils.RedisOperator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CoderZk
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisOperator redisOperator;

    @GetMapping("/set")
    public String set(String key, String value) {
//        redisTemplate.opsForValue().set(key, value);
        redisOperator.set(key, value);
        return "OK";
    }

    @GetMapping("/get")
    public String get(String key) {
//        return  (String) redisTemplate.opsForValue().get(key);
        return redisOperator.get(key);
    }

    @GetMapping("/delete")
    public Object delete(String key) {
//        redisTemplate.delete(key);
        redisOperator.del(key);
        return "OK";
    }

    /**
     * 大量key查询
     *
     * @param keys
     * @return
     */
    @GetMapping("/getALot")
    public Object getALot(String... keys) {

        List<String> result = new ArrayList<>();
        for (String key : keys) {
            result.add(redisOperator.get(key));
        }

        return result;
    }

    /**
     * 批量查询 mget
     *
     * @param keys
     * @return
     */
    @GetMapping("/mget")
    public Object mget(String... keys) {
        List<String> keysList = Arrays.asList(keys);
        return redisOperator.mget(keysList);
    }

    /**
     * 批量查询 pipeline
     *
     * @param keys
     * @return
     */
    @GetMapping("/batchGet")
    public Object batchGet(String... keys) {
        List<String> keysList = Arrays.asList(keys);
        return redisOperator.batchGet(keysList);
    }
}
