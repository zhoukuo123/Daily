package com.zk.annotation;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author CoderZk
 */
@Aspect
@Component
public class AccessLimiterAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisScript<Boolean> rateLimitLua;


    @Pointcut("@annotation(com.zk.annotation.AccessLimiter)")
    public void cut() {
//        log.info("cut");
    }

    @Before("cut()")
    public void before(JoinPoint joinpoint) {
        // 1. 获得方法签名, 作为method Key
        MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        Method method = signature.getMethod();

        AccessLimiter annotation = method.getAnnotation(AccessLimiter.class);
//        if (annotation == null) {
//            return;
//        }

        String key = annotation.methodKey();
        String limit = annotation.limit();

        // 如果没设置methodKey, 从调用方法签名自动生成一个key
        if (StringUtils.isBlank(key)) {
            Class[] type = method.getParameterTypes();
            key = method.getName();

            if (type != null) {
                String paramType = Arrays.stream(type)
                        .map(Class::getName)
                        .collect(Collectors.joining(","));
                key += "#" + paramType;
            }
        }

        // 2. 调用Redis
        boolean acquired = stringRedisTemplate.execute(
                rateLimitLua, // Lua script的真身
                Lists.newArrayList(key), // Lua脚本中的Key列表
                limit // Lua脚本Value列表
        );

        if (!acquired) {
//            log.error("your access is blocked, key={}", key);
            throw new RuntimeException("Your access is blocked");
        }
    }
}
