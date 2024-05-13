package com.one.kafka.api.server;

import com.one.kafka.api.request.ProductRequest;
import com.one.kafka.broker.consumer.ProductConsumer;
import com.one.kafka.broker.message.ProductMessage;
import com.one.kafka.command.service.ProductFeedBackService;
import com.one.kafka.entity.Product;
import com.one.kafka.scheduler.ProductScheduler;
import com.one.kafka.service.CreateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductFeedBackAPI {

    @Autowired
    private ProductFeedBackService productFeedBackService;

    @Autowired
    private ProductConsumer productConsumer;

    @Autowired
    private CreateProductService createProductService;


    @PostMapping(value = "/product_feedback",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postProduct(@RequestBody ProductRequest productRequest) {
        return postProductInternal(productRequest);
    }

    protected ResponseEntity<String> postProductInternal(ProductRequest productRequest) {
        productFeedBackService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product Created");
    }

    @GetMapping(value = "/all_user_product")
    public List<ProductMessage> getAllProducts() {
        return productConsumer.getProductMessageList();
    }

    @GetMapping(value = "/inventory")
    public List<Product> list_inventory() {
        return createProductService.fetchInventory();
    }

    @PostMapping(value = "/inventory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProductToInventory(@RequestBody Product product) {
        createProductService.createAndAddToInventoryProduct();
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added to inventory");
    }
}
