package test.kafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import test.kafka.dto.User;

@Component
@Slf4j
public class KafkaConsumerWithAck {

    @KafkaListener(id = "listener_ack", topics="demo_spring", groupId = "spring-test")
    public void listener(@Headers MessageHeaders messageHeaders, @Payload User user){
        log.info("Received message: header={}, payload name={}, age={}", messageHeaders, user.getName(), user.getAge());
    }

}
