package com.one.kafka.broker.message;

import java.util.Map;

public class ProductFeedBackStreamMessage {

    private String productName;
    private double averageRating;

    private Map<Integer,Long> starMap;

    public ProductFeedBackStreamMessage() {}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Map<Integer, Long> getStarMap() {
        return starMap;
    }

    public void setStarMap(Map<Integer, Long> starMap) {
        this.starMap = starMap;
    }

    @Override
    public String toString() {
        return "ProductFeedBackStreamMessage{" +
                "productName='" + productName + '\'' +
                ", averageRating=" + averageRating +
                ", starMap=" + starMap +
                '}';
    }
}
