package com.one.kafka.broker.stream.product_feedback.rating;

import com.one.kafka.broker.message.ProductFeedBackStreamMessage;
import com.one.kafka.broker.message.ProductMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ProductFeedBackValueTransformer implements ValueTransformer<ProductMessage, ProductFeedBackStreamMessage> {

    private ProcessorContext processorContext;

    private final String storeStateName;

    private KeyValueStore<String,ProductFeedBackStoreValue> storeValueKeyValue;

    public ProductFeedBackValueTransformer(String storeStateName) {
        if (StringUtils.isEmpty(storeStateName)) {
            throw new IllegalArgumentException("Store State Name is Empty!!!!");
        }
        this.storeStateName = storeStateName;
    }

    @Override
    public void init(ProcessorContext processorContext) {
        this.processorContext = processorContext;
        this.storeValueKeyValue = this.processorContext.getStateStore(storeStateName);
    }

    @Override
    public ProductFeedBackStreamMessage transform(ProductMessage productFeedBackMessage) {

        var storeValue = Optional.ofNullable(storeValueKeyValue.get(productFeedBackMessage.getProductName()))
                .orElse(new ProductFeedBackStoreValue());

        var startMap = Optional.ofNullable(storeValue.getStartMap()).orElse(new TreeMap<>());

        var currentRating = Optional.ofNullable(startMap.get(productFeedBackMessage.getRating())).orElse(0l);

        startMap.put(productFeedBackMessage.getRating(),currentRating+1);
        storeValueKeyValue.put(productFeedBackMessage.getProductName(),storeValue);

        var newRating = getProductFeedBackStreamMessage(productFeedBackMessage, startMap);
        return newRating;
    }

    private static ProductFeedBackStreamMessage getProductFeedBackStreamMessage(ProductMessage productFeedBackMessage, Map<Integer, Long> startMap) {
        var newRating = new ProductFeedBackStreamMessage();
        newRating.setProductName(productFeedBackMessage.getProductName());
        newRating.setStarMap(startMap);

        var sumRating = 0l;
        var countRating = 0l;

        for (var entry : startMap.entrySet()) {
            sumRating += entry.getKey() * entry.getValue();
            countRating += entry.getValue();
        }
        if (countRating != 0) {
            var averageRating = Math.round((double) sumRating / countRating * 10d) / 10d;
            newRating.setAverageRating(averageRating);
            System.out.println("Average Rating: " + averageRating);
        } else {
            System.out.println("No ratings provided.");
        }
        return newRating;
    }

    @Override
    public void close() {

    }
}
