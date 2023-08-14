package test.kafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics="demo", groupId = "spring-test")
    public void listener(String data){
        log.info("Received message={}", data);
    }

}
