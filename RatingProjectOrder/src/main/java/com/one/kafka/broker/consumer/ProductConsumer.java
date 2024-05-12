package com.one.kafka.broker.consumer;

import com.one.kafka.broker.message.ProductMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);

    private List<ProductMessage> productMessageList = new ArrayList<>();

    @KafkaListener(topics = "t-product-order",containerFactory = "productOrderContainerFactory")
    public void consumeProduct(ProductMessage productMessage) {
        productMessageList.add(productMessage);
        LOGGER.info("Product Received : {}",productMessage);
    }

    public List<ProductMessage> getProductMessageList() {
        return productMessageList;
    }
}
