package test.kafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class KafkaProducerForString {

    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final String topic = "demo_spring";

    @Autowired
    public KafkaProducerForString(KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message){
        kafkaTemplate.send(topic, message);
        log.info("Produced message={}", message);
    }
}
