package net.javaguides.springboot;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    //private KafkaConsumer<String, String> consumer;

    @KafkaListener(topics = "wikimedia_recentchanges", groupId = "myGroup")
    public void consume(String eventMessage) {
        LOGGER.info("event message received: {}", eventMessage);
    }
}
