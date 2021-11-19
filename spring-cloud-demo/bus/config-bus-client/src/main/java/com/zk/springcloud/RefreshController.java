package com.zk.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CoderZk
 */
@RestController
@RequestMapping("/refresh")
@RefreshScope
public class RefreshController {

    @Value("${words}")
    private String words;

    @GetMapping("/words")
    public String getWords() {
        return words;
    }

}
