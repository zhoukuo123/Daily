package com.zk.springcloud.biz;

import com.zk.springcloud.topic.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author CoderZk
 */
@Slf4j
@EnableBinding(value = {
        Sink.class,
        MyTopic.class,
        GroupTopic.class,
        DelayedTopic.class,
        ErrorTopic.class,
        RequeueTopic.class
}
)
public class StreamConsumer {

    private AtomicInteger count = new AtomicInteger(1);

    @StreamListener(Sink.INPUT)
    public void consume(Object payload) {
        log.info("message consumed successfully, payload={}", payload);
    }

    @StreamListener(MyTopic.INPUT)
    public void consumeMyMessage(Object payload) {
        log.info("My message consumed successfully, payload={}", payload);
    }

    // 消息分组 & 消息分区示例
    @StreamListener(GroupTopic.INPUT)
    public void consumeGroupMessage(Object payload) {
        log.info("Group message consumed successfully, payload={}", payload);
    }

    // 延迟消息示例
    @StreamListener(DelayedTopic.INPUT)
    public void consumeDelayedMessage(MessageBean bean) {
        log.info("Delayed message consumed successfully, payload={}", bean.getPayload());
    }

    // 异常重试(单机版)
    @StreamListener(ErrorTopic.INPUT)
    public void consumeErrorMessage(MessageBean bean) {
        log.info("Are you OK?");

        if (count.incrementAndGet() % 3 == 0) {
            log.info("Fine, thank you. And you?");
            count.set(0);
        } else {
            log.info("What's your problem?");
            throw new RuntimeException("I'm not OK");
        }
    }

    // 异常重试(联机版 - 重新入列)
    @StreamListener(RequeueTopic.INPUT)
    public void requeueErrorMessage(MessageBean bean) {
        log.info("Are you OK?");

        try {
            Thread.sleep(3000L);
        } catch (Exception e) {
        }

        throw new RuntimeException("I'm not OK");
    }
}
