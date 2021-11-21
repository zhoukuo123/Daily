package com.zk.auth.service;

import com.zk.auth.service.pojo.Account;
import com.zk.auth.service.pojo.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author CoderZk
 */
@FeignClient("foodie-auth-service")
@RequestMapping("/auth-service")
public interface AuthService {

    @PostMapping("/token")
    AuthResponse tokennize(@RequestParam("userId") String userId);

    @PostMapping("/verify")
    AuthResponse verify(@RequestBody Account account);

    @PostMapping("/refresh")
    AuthResponse refresh(@RequestParam("refresh") String refresh);

    @DeleteMapping("/delete")
    AuthResponse delete(@RequestBody Account account);
}
