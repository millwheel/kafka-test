package test.kafka.messaging;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics="demo_spring", groupId = "spring-test")
    public void listener(String message){
        JSONObject jsonObject = new JSONObject(message);
        JSONObject data = jsonObject.getJSONObject("data");
        String name = data.getString("name");
        int age = data.getInt("age");
        log.info("Received message: name={}, age={}", name, age);
    }

}
