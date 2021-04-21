package com.zk.spring.ioc.context;

/**
 * @author CoderZk
 */
public interface ApplicationContext {
    /**
     * 从容器中提取bean
     *
     * @param beanId
     * @return
     */
    Object getBean(String beanId);
}
