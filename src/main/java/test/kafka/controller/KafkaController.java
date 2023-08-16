package test.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.kafka.dto.User;
import test.kafka.messaging.KafkaProducerForJson;
import test.kafka.messaging.KafkaProducerForString;
import test.kafka.messaging.KafkaProducerWithCallback;

@RestController
@Slf4j
public class KafkaController {

    private final KafkaProducerForString kafkaProducerForString;

    public KafkaController(KafkaProducerForString kafkaProducerForString) {
        this.kafkaProducerForString = kafkaProducerForString;
    }

    @PostMapping("/publish/string")
    public void sendString(@RequestBody String data){
        kafkaProducerForString.sendMessage(data);
    }

//    @PostMapping("/publish/json")
//    public void sendJson(@RequestBody User user){
//        kafkaProducerForJson.sendMessage(user);
//    }
//
//    @PostMapping("/publish/callback")
//    public void sendMessageWithCallback(@RequestBody User user){
//        kafkaProducerWithCallback.sendMessage(user);
//    }

}
