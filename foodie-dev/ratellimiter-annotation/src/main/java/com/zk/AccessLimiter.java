package com.zk;

import com.google.common.collect.Lists;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CoderZk
 */
@Service
@Deprecated
public class AccessLimiter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisScript<Boolean> rateLimitLua;

    public void limitAccess(String key, Integer limit) {
        // step 1 : request Lua script
        boolean acquired = stringRedisTemplate.execute(
                rateLimitLua, // Lua script的真身
                Lists.newArrayList(key), // Lua脚本中的Key列表
                limit.toString() // Lua脚本Value列表
        );

        if (!acquired) {
//            log.error("your access is blocked, key={}", key);
            throw new RuntimeException("Your access is blocked");
        }
    }
}
