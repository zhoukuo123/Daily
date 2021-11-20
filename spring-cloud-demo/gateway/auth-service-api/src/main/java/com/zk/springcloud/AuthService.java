package com.zk.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CoderZk
 */
@FeignClient("auth-service")
@RestController
public interface AuthService {

    @PostMapping("/login")
    AuthResponse login(@RequestParam("username") String username,
                       @RequestParam("password") String password);

    @GetMapping("/verify")
    AuthResponse verify(@RequestParam("token") String token,
                        @RequestParam("username") String username);

    @PostMapping("/refresh")
    AuthResponse refresh(@RequestParam("refresh") String refresh);

}
