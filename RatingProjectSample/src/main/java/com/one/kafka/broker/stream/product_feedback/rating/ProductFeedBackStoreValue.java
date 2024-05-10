package com.one.kafka.broker.stream.product_feedback.rating;

import java.util.HashMap;
import java.util.Map;

public class ProductFeedBackStoreValue {

    private Map<Integer,Long> startMap = new HashMap<>();

    public Map<Integer, Long> getStartMap() {
        return startMap;
    }

    public void setStartMap(Map<Integer, Long> startMap) {
        this.startMap = startMap;
    }


    @Override
    public String toString() {
        return "ProductFeedBackStoreValue{" +
                "startMap=" + startMap +
                '}';
    }
}
