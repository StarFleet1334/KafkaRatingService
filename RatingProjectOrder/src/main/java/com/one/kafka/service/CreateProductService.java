package com.one.kafka.service;


import com.one.kafka.broker.message.ProductMessage;
import com.one.kafka.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class CreateProductService {

    private final List<Product> inventory = Collections.synchronizedList(new ArrayList<>());


    private final Random random = new Random();
    private final String[] productNames = {"Widget", "Gadget", "Thingamajig", "Doodad", "Whatchamacallit"};
    private final String[] descriptions = {"A useful tool", "An innovative gadget", "A versatile product", "An essential item", "A high-quality device"};

    private final String inventoryEndpoint = "http://localhost:9001/api/inventory";

    private RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductService.class);

    private Product latest;

    public Product createProduct() {
        Product product = new Product();
        product.setProductName(randomProductName());
        product.setProduct_price(randomProductPrice());
        product.setDescription(randomDescription());
        latest = product;
        return product;
    }

    public Product createAndAddToInventoryProduct() {
        inventory.add(latest);
        return latest;
    }

    public List<Product> fetchInventory() {
        return inventory;
    }

    private String randomProductName() {
        int index = random.nextInt(productNames.length);
        return productNames[index];
    }

    private double randomProductPrice() {
        return 10 + (1000 - 10) * random.nextDouble();  // Generates a price between 10 and 1000
    }

    private String randomDescription() {
        int index = random.nextInt(descriptions.length);
        return descriptions[index];
    }

}
