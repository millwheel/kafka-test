package test.kafka.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.kafka.dto.User;
import test.kafka.messaging.KafkaProducerForJson;
import test.kafka.messaging.KafkaProducerWithCallback;

@RestController
public class KafkaController {

//    private final KafkaProducerForString kafkaProducerForString;
    private final KafkaProducerForJson kafkaProducerForJson;
    private final KafkaProducerWithCallback kafkaProducerWithCallback;

    public KafkaController(KafkaProducerForJson kafkaProducerForJson, KafkaProducerWithCallback kafkaProducerWithCallback) {
        this.kafkaProducerForJson = kafkaProducerForJson;
        this.kafkaProducerWithCallback = kafkaProducerWithCallback;
    }

//    @PostMapping("/publish/string")
//    public void sendString(@RequestBody String data){
//        kafkaProducerForString.sendMessage(data);
//    }

    @PostMapping("/publish/json")
    public void sendJson(@RequestBody User user){
        kafkaProducerForJson.sendMessage(user);
    }

    @PostMapping("/publish/callback")
    public void sendMessageWithCallback(@RequestBody User user){
        kafkaProducerWithCallback.sendMessage(user);
    }

}
