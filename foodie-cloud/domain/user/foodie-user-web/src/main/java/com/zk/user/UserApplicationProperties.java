package com.zk.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author CoderZk
 */
@Configuration
@RefreshScope
@Data
public class UserApplicationProperties {

    @Value("${userservice.registration.disabled}")
    private boolean disableRegistration;

}
