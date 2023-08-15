package test.kafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import test.kafka.dto.User;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaProducerForJson {

    private final KafkaTemplate<String, User> kafkaTemplate;
    private final String topic = "demo_spring";

    @Autowired
    public KafkaProducerForJson(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user){
        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        kafkaTemplate.send(message);
        log.info("produced message: name={}, age={}", message.getPayload().getName(), message.getPayload().getAge());

    }
}