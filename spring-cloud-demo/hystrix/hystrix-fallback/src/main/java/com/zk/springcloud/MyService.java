package com.zk.springcloud;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author CoderZk
 */
@FeignClient(name = "feign-client") // 此处名字是你要调用的服务
public interface MyService extends IService {

}
