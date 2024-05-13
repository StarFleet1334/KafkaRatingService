package com.one.kafka.entity;

public class Product {

    private String productName;
    private double product_price;
    private String description;

    public Product() {}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", product_price=" + product_price +
                ", description='" + description + '\'' +
                '}';
    }
}
