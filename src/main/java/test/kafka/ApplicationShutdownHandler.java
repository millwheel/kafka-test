package test.kafka;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.Collection;


//@Component
@Slf4j
public class ApplicationShutdownHandler {

    private final KafkaListenerEndpointRegistry registry;

    @Autowired
    public ApplicationShutdownHandler(KafkaListenerEndpointRegistry registry) {
        this.registry = registry;
    }

//    @PreDestroy
    public void onExit() {
        log.info("Server is starting to shut down");
        try {
            MessageListenerContainer container = registry.getListenerContainer("listener_shutdown");
            Collection<MessageListenerContainer> allListenerContainers = registry.getAllListenerContainers();
            allListenerContainers.forEach(System.out::println);
            container.stop();
        } catch (Exception e) {
            log.error("Error occurred while stopping Kafka listener container: {}", e.getMessage());
        }
        log.info("Application shut down gracefully");
    }



}
