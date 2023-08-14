package test.kafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import test.kafka.dto.User;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics="demo_spring", groupId = "spring-test")
    public void listener(@Headers MessageHeaders messageHeaders, @Payload User user){
        log.info("Received message: header={}, payload name={}, age={}", messageHeaders, user.getName(), user.getAge());
    }

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

}
