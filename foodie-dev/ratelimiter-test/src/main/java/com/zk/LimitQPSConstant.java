package com.zk;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author CoderZk
 */
@Component
@ConfigurationProperties(prefix = "limit")
@PropertySource("classpath:accessLimiter.properties")
public class LimitQPSConstant {

    public String apiCreateVmInstanceMsgQPS;

    public void setApiCreateVmInstanceMsgQPS(String apiCreateVmInstanceMsgQPS) {
        this.apiCreateVmInstanceMsgQPS = apiCreateVmInstanceMsgQPS;
    }

    private String apiCreateVmNicMsgQPS;

    public String getApiCreateVmInstanceMsgQPS() {
        return apiCreateVmInstanceMsgQPS;
    }


    public String getApiCreateVmNicMsgQPS() {
        return apiCreateVmNicMsgQPS;
    }

    public void setApiCreateVmNicMsgQPS(String apiCreateVmNicMsgQPS) {
        this.apiCreateVmNicMsgQPS = apiCreateVmNicMsgQPS;
    }
}
