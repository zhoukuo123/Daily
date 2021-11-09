package com.zk.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CoderZk
 */
@RestController
@Slf4j
public class Controller {

    @Reference(loadbalance = "roundrobin")
    private IDubboService dubboService;

    @PostMapping("/publish")
    public Product publish(@RequestParam String name) {
        Product product = new Product();
        product.setName(name);

        return dubboService.publish(product);
    }
}
