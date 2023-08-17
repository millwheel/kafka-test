package test.kafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
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
public class KafkaProducerWithCallback {

    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final String topic = "demo_spring";

    @Autowired
    public KafkaProducerWithCallback(KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User user){
        Message<User> message = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, topic).build();

        CompletableFuture<SendResult<Object, Object>> future = kafkaTemplate.send(message);
        future.whenComplete((result, e) -> {
           if (e == null){
               int partition = result.getRecordMetadata().partition();
               long offset = result.getRecordMetadata().offset();
               User sentUser = (User) result.getProducerRecord().value();
               log.info("produced message topic={}, partition={}, offset={}, payload: name={}, age={}",
                       topic, partition, offset, sentUser.getName(), sentUser.getAge());
           } else{
               log.error("Error occurred while producing message: {}", e.getMessage());
           }
        });
    }
}
