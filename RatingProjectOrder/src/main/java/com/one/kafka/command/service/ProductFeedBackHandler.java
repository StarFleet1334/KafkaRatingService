package com.one.kafka.command.service;

import com.one.kafka.api.request.ProductRequest;
import com.one.kafka.api.server.ProductFeedBackAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductFeedBackHandler {

    @Autowired
    private ProductFeedBackAPI productFeedBackAPI;

    public void handleProductFeedBack(ProductRequest productRequest) {
        ResponseEntity<String> response = productFeedBackAPI.postProduct(productRequest);
        System.out.println("Response from posting product: " + response.getBody());
    }
}
