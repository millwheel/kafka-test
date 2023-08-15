package test.kafka;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationShutdownHandler {

    private final KafkaListenerEndpointRegistry registry;

    @Autowired
    public ApplicationShutdownHandler(KafkaListenerEndpointRegistry registry) {
        this.registry = registry;
    }

    @PreDestroy
    public void onExit() {
        log.info("Server is starting to shut down");
        try {
            registry.getListenerContainer("listener_ack").stop();
        } catch (Exception e) {
            log.error("Error occurred while stopping Kafka listener container: {}", e.getMessage());
        }
        log.info("Application shut down gracefully");
    }

}
