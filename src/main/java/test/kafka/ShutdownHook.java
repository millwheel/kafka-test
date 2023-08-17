package test.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.kafka.messaging.KafkaConsumerWithShutdown;

@Component
@Slf4j
public class ShutdownHook implements DisposableBean {

    private final KafkaConsumerWithShutdown consumer;

    @Autowired
    public ShutdownHook(KafkaConsumerWithShutdown consumer) {
        this.consumer = consumer;
    }

    @Override
    public void destroy() {
        long lastConsumedOffset = consumer.getLastConsumedOffset();
        long lastPartition = consumer.getLastPartition();
        log.info("Last consumed offset before shutdown: " + lastConsumedOffset);
        log.info("Last partition before shutdown: " + lastPartition);
    }
}