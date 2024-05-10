package com.one.kafka.broker.stream.product_feedback.rating;


import com.one.kafka.broker.message.ProductFeedBackStreamMessage;
import com.one.kafka.broker.message.ProductMessage;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.Stores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class ProductFeedBackStream {


    @Bean
    public KStream<String, ProductMessage> kStream(StreamsBuilder streamsBuilder) {

        var keySerde = Serdes.String();

        var productSerde = new JsonSerde<>(ProductMessage.class);

        var productFeedBackSerde = new JsonSerde<>(ProductFeedBackStreamMessage.class);

        var productStoreValue = new JsonSerde<>(ProductFeedBackStoreValue.class);

        var sourceStream = streamsBuilder.stream("t-product-order", Consumed.with(keySerde,productSerde));


        var storeName = "productFeedBackStateStore";
        var storeSupplier = Stores.inMemoryKeyValueStore(storeName);
        var storeBuilder = Stores.keyValueStoreBuilder(storeSupplier,keySerde,productStoreValue);

        streamsBuilder.addStateStore(storeBuilder);

        sourceStream.transformValues(() -> new ProductFeedBackValueTransformer(storeName),storeName)
                .to("t-product-order-rating", Produced.with(keySerde,productFeedBackSerde));




        return sourceStream;


    }

}
