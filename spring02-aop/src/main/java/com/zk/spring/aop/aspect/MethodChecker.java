package com.zk.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CoderZk
 */
public class MethodChecker {
    /**
     * 环绕通知的描述方法
     *
     * @param pjp ProceedingJoinPoint 是 JoinPoint 的升级版, 在原有功能外, 还可以控制目标方法是否执行
     * @return Object 是目标方法执行后的返回值
     */
    public Object check(ProceedingJoinPoint pjp) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();
            // 执行目标方法
            Object ret = pjp.proceed();
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            if (duration >= 1000) {
                String className = pjp.getTarget().getClass().getName();
                String methodName = pjp.getSignature().getName();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                String now = sdf.format(new Date());
                System.out.println("=======" + now + ":" + className + "." + methodName
                        + "(" + duration + "ms)=========");
            }
            return ret;
        } catch (Throwable throwable) {
            System.out.println("Exception message:" + throwable.getMessage());
            throw throwable;
        }
    }
}
