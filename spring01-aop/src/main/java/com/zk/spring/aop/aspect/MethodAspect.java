package com.zk.spring.aop.aspect;


import org.aspectj.lang.JoinPoint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CoderZk
 * <p>
 * 切面类
 */
public class MethodAspect {
    /**
     * 切面方法, 用于拓展额外功能
     *
     * @param joinpoint 连接点, 通过连接点可以获取目标类/目标方法的信息
     */
    public void printExecutionTime(JoinPoint joinpoint) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String now = sdf.format(new Date());
        // 获取目标类的名称
        String className = joinpoint.getTarget().getClass().getName();
        // 获取目标方法名称
        String methodName = joinpoint.getSignature().getName();
        System.out.println("---->" + now + ":" + className + "." + methodName);

        // 获取目标方法参数值
        Object[] args = joinpoint.getArgs();
        System.out.println("---->参数个数:" + args.length);
        for (Object arg : args) {
            System.out.println("---->参数:" + arg);
        }
    }

    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        System.out.println(ret);
    }

    public void doAfterThrowing(JoinPoint joinPoint, Throwable th) {
        System.out.println(th.getMessage());
    }

    public void doAfter(JoinPoint joinPoint) {
        System.out.println("触发后置通知");
    }
}