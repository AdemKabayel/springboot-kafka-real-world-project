package net.javaguides.springboot.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class WikimediaChangesHandler implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void onOpen() throws Exception {
        LOGGER.info("onOpen");
    }

    @Override
    public void onClosed() throws Exception {
        LOGGER.info("onClosed");
    }

    /* whenever there is a new event/ event changes handler above will get triggered and then this onMessage method
    will send the message using kafkaTemplate.send() method.
    * */
    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {

        LOGGER.info("event data: {}", messageEvent);
        kafkaTemplate.send(topic, messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {
        LOGGER.info("comment data: {}", s);
    }

    @Override
    public void onError(Throwable throwable) {
        LOGGER.error("error", throwable);

    }
}
