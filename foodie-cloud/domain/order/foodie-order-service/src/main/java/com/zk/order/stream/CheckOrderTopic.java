package com.zk.order.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author CoderZk
 */
public interface CheckOrderTopic {
    String INPUT = "orderstatus-consumer";
    String OUTPUT = "orderstatus-producer";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();
}
