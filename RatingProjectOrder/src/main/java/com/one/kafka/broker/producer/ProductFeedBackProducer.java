package com.one.kafka.broker.producer;

import com.one.kafka.broker.message.ProductMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductFeedBackProducer {

    @Autowired
    private KafkaTemplate<String,ProductMessage> kafkaTemplate;

    public void publish(ProductMessage product) {
        kafkaTemplate.send("t-product-order",product);

    }
}
