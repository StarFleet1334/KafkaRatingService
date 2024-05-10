package com.one.kafka.api.server;

import com.one.kafka.api.request.ProductRequest;
import com.one.kafka.command.service.ProductFeedBackHandler;
import com.one.kafka.config.JsonReader;
import com.one.kafka.network.PostProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRequestInitiator {

    @Autowired
    private ProductFeedBackHandler productFeedbackHandler;

    public void createAndSendProductFeedback(int number_of_products,int delay) {
        ProductRequest request = new ProductRequest("Sample Product", 5);

        try {
            JsonReader reader = new JsonReader();
            List<ProductRequest> myList = reader.readJsonFile("templates/products.json");

            for (int i = 0; i < number_of_products; i++) {
                productFeedbackHandler.handleProductFeedBack(myList.get(i));
                Thread.sleep(delay);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
