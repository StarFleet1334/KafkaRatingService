package com.one.kafka.api.request;

public class ProductRequest {
    private String productName;
    private int rating;

    public ProductRequest() {}

    public ProductRequest(String productName, int productRating) {
        this.productName = productName;
        this.rating = productRating;
    }

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
        return "ProductRequest{" +
                "productName='" + productName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
