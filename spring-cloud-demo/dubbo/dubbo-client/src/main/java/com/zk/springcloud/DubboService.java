package com.zk.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author CoderZk
 */
// 注意是org.apache.dubbo自己的Service注解, 不是springframework的
@Service
@Slf4j
public class DubboService implements IDubboService {

    @Override
    public Product publish(Product product) {
        log.info("Publishing product {}", product.getName());
        return product;
    }
}
