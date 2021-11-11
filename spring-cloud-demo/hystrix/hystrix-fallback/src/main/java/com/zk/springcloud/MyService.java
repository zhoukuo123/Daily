package com.zk.springcloud;

import com.zk.springcloud.hystrix.Fallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author CoderZk
 */
@FeignClient(name = "feign-client", fallback = Fallback.class, primary = true) // 此处名字是你要调用的服务
public interface MyService extends IService {

}
