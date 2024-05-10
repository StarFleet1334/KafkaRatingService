package com.one.kafka.command.action;

import com.one.kafka.api.request.ProductRequest;
import com.one.kafka.broker.message.ProductMessage;
import com.one.kafka.broker.producer.ProductFeedBackProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductFeedBackAction {

    @Autowired
    private ProductFeedBackProducer productFeedBackProducer;


    public void publishProductToKafkaTopic(ProductRequest productRequest) {
        var product = new ProductMessage();
        product.setProductName(productRequest.getProductName());
        product.setRating(productRequest.getRating());
        productFeedBackProducer.publish(product);
    }
}
