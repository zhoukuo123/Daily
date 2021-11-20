package com.zk.springcloud;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author CoderZk
 */
@Slf4j
@Service
public class JwtService {

    // 生成环境不能这么用
    private static final String KEY = "changIt";
    private static final String ISSUER = "yao";
    private static final long TOEKN_EXP_TIME = 60000;
    private static final String USER_NAME = "username";

    /**
     * 生成Token
     * @param acct
     * @return
     */
    public String token(Account acct) {
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(KEY);

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + TOEKN_EXP_TIME))
                .withClaim(USER_NAME, acct.getUsername())
//                .withClaim("ROLE", "")
                .sign(algorithm);

        log.info("jwt generated user={}", acct.getUsername());
        return token;
    }

    /**
     * 校验Token  token的验证服务 验证token是否是属于当前用户的
     * @param token
     * @param username
     * @return
     */
    public boolean verify(String token, String username) {
        log.info("verifying jwt - username={}", username);

        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withClaim(USER_NAME, username)
                    .build();

            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("auth failed", e);
            return false;
        }
    }
}
