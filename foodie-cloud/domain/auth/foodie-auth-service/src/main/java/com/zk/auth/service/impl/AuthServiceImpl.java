package com.zk.auth.service.impl;

import com.zk.auth.service.AuthService;
import com.zk.auth.service.pojo.Account;
import com.zk.auth.service.pojo.AuthCode;
import com.zk.auth.service.pojo.AuthResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

/**
 * @author CoderZk
 */
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String USER_TOKEN = "USER_TOKEN-";

    @Override
    public AuthResponse tokennize(String userId) {
        Account account = Account.builder()
                .userId(userId)
                .build();

        String token = jwtService.token(account);
        account.setToken(token);
        account.setRefreshToken(UUID.randomUUID().toString());

        redisTemplate.opsForValue().set(USER_TOKEN + userId, account);
        redisTemplate.opsForValue().set(account.getRefreshToken(), userId);
        return AuthResponse.builder()
                .account(account)
                .code(AuthCode.SUCCESS.getCode())
                .build();
    }

    @Override
    public AuthResponse verify(Account account) {
        boolean success = jwtService.verify(account.getToken(), account.getUserId());
        return AuthResponse.builder()
                .code(success ? AuthCode.SUCCESS.getCode() : AuthCode.USER_NOT_FOUND.getCode())
                .build();
    }

    // 有很多种方法实现自动刷新，比如前端主动调用（可以在AuthResponse里将过期时间返回给前端口）
    @Override
    public AuthResponse refresh(String refreshToken) {
        String userId = (String) redisTemplate.opsForValue().get(refreshToken);
        if (StringUtils.isBlank(userId)) {
            return AuthResponse.builder()
                    .code(AuthCode.USER_NOT_FOUND.getCode())
                    .build();
        }
        redisTemplate.delete(refreshToken);
        return tokennize(userId);
    }

    @Override
    public AuthResponse delete(Account account) {
        AuthResponse token = verify(account);

        AuthResponse resp = new AuthResponse();
        if (AuthCode.SUCCESS.equals(token.getCode())) {
            redisTemplate.delete(account.getRefreshToken());
            redisTemplate.delete(account.getUserId());
            resp.setCode(AuthCode.SUCCESS.getCode());
        } else {
            resp.setCode(AuthCode.USER_NOT_FOUND.getCode());
        }
        return resp;
    }
}
