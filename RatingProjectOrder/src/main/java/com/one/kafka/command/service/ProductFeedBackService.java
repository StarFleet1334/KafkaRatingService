package com.one.kafka.command.service;

import com.one.kafka.api.request.ProductRequest;
import com.one.kafka.command.action.ProductFeedBackAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductFeedBackService {

    @Autowired
    private ProductFeedBackAction productFeedBackAction;

    public void createProduct(ProductRequest productRequest) {
        productFeedBackAction.publishProductToKafkaTopic(productRequest);
    }
}
