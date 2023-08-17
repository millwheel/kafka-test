package test.kafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import test.kafka.dto.User;

@Component
@Slf4j
public class KafkaConsumerWithShutdown {

    private volatile long lastConsumedOffset = -1;
    private volatile long lastPartition = -1;

    @KafkaListener(id = "listener_shutdown", topics = "demo_spring", groupId = "spring-test")
    public void listener(@Headers MessageHeaders messageHeaders, @Payload User user, ConsumerRecord<?, ?> record) {
        log.info("Received message: header={}, payload name={}, age={}", messageHeaders, user.getName(), user.getAge());
        long offset = record.offset();
        lastConsumedOffset = offset;
        long partition = record.partition();
        lastPartition = partition;
    }

    public long getLastConsumedOffset() {
        return lastConsumedOffset;
    }

    public long getLastPartition(){
        return lastPartition;
    }
}
