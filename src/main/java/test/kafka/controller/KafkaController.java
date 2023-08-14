package test.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.kafka.dto.User;
import test.kafka.messaging.KafkaProducer;

@RestController
@Slf4j
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public void sendMessage(@RequestBody User user){
        log.info(user.getName(), user.getAge());
        JSONObject jsonObject = new JSONObject();
        JSONObject data = new JSONObject(user);
        jsonObject.put("data", data);
        kafkaProducer.sendMessage(jsonObject.toString());
    }
}
