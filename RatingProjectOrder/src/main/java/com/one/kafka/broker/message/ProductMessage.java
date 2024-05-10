package com.one.kafka.broker.message;

public class ProductMessage {

    private String productName;
    private int rating;

    public ProductMessage() {}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProductMessage{" +
                "productName='" + productName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
