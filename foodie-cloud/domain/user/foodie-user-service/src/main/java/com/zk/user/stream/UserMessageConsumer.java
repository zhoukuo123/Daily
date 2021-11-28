package com.zk.user.stream;

import com.zk.auth.service.AuthService;
import com.zk.auth.service.pojo.Account;
import com.zk.auth.service.pojo.AuthCode;
import com.zk.auth.service.pojo.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

/**
 * @author CoderZk
 */
@Slf4j
@EnableBinding(value = {
        ForceLogoutTopic.class
})
public class UserMessageConsumer {

    @Autowired
    private AuthService authService;

    @StreamListener(ForceLogoutTopic.INPUT)
    public void consumeLogoutMessage(String payload) {
        log.info("Force logout user, uid={}", payload);

        Account account = Account.builder().userId(payload).skipVerification(true).build();
        AuthResponse resp = authService.delete(account);
        if (!AuthCode.SUCCESS.getCode().equals(resp.getCode())) {
            log.error("Error occurred when deleting user session, uid={}", payload);
            throw new RuntimeException("Cannot delete user session");
        }
    }

    // 1) 重试 & requeue
    // 2) 死信队列 & 服务降级
    @ServiceActivator(inputChannel = "force-logout-topic.force-logout-group.errors")
    public void fallback(Message<?> message) {
        log.info("Force logout failed");
        // 新零售发布库存 - 异步请求
        // 降级: 钉钉群的接口 - 通知运营

        // 强制登出 - inactive user
        // user -> flag(active/inactive) -> 不让你下次登录
    }
}
