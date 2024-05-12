package com.one.kafka.error.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;


public class GlobalErrorHandler implements CommonErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandler.class);

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final String deadMessageTopic;

    public GlobalErrorHandler(KafkaTemplate<String,String> kafkaTemplate, String deadMessageTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.deadMessageTopic = deadMessageTopic;
    }

    @Override
    public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        LOGGER.warn("Global error handler for message: {}", record.value());
        try {
            kafkaTemplate.send(deadMessageTopic, record.value().toString());
            LOGGER.info("Sent to dead-letter topic: {}", deadMessageTopic);
        } catch (Exception e) {
            LOGGER.error("Failed to send to dead-letter topic: {} with exception: {}", deadMessageTopic, e.getMessage());
        }
        return true;
    }
}
