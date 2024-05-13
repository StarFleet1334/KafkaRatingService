package com.one.kafka.scheduler;


import com.one.kafka.service.CreateProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductScheduler.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CreateProductService createProductService;

    private final String inventoryEndpoint = "http://localhost:9001/api/inventory";



    @Scheduled(fixedRate = 5000)
    public void addProductToInventory() {
        var product = createProductService.createProduct();
        restTemplate.postForObject(inventoryEndpoint,product,String.class);
        LOGGER.info("Successfully added product to inventory: {}",product);
    }

}
