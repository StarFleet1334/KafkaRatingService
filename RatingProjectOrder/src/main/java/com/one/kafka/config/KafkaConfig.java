package com.one.kafka.config;


import com.one.kafka.error.handler.GlobalErrorHandler;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.NoSuchSslBundleException;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.function.Consumer;

@Configuration
@EnableKafka
public class KafkaConfig {


    @Bean
    public NewTopic topicOrder() {
        return TopicBuilder.name("t-product-order").partitions(1).replicas(1).build();
    }

    @Autowired
    private KafkaProperties kafkaProperties;


    @Bean(name = "productOrderContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object,Object> productOrderContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            KafkaTemplate<String,String> kafkaTemplate
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<Object,Object>();
        configurer.configure(factory,consumerFactory());


        factory.setCommonErrorHandler(new GlobalErrorHandler(kafkaTemplate,"dead-product-order"));

        return factory;
    }

    private ConsumerFactory<Object, Object> consumerFactory() {
        SslBundles sslBundles = getOrCreateSslBundles(); // You need to implement this method based on your application's requirements

        var properties = kafkaProperties.buildConsumerProperties(sslBundles);
        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");

        return new DefaultKafkaConsumerFactory<>(properties);
    }

    private SslBundles getOrCreateSslBundles() {
        // Your logic to create or retrieve an SslBundles instance
        // This could be as simple as a new instance or as complex as you need it to be
        // Example: return new SslBundles();
        return new SslBundles() {
            @Override
            public SslBundle getBundle(String name) throws NoSuchSslBundleException {
                return null;
            }

            @Override
            public void addBundleUpdateHandler(String name, Consumer<SslBundle> updateHandler) throws NoSuchSslBundleException {

            }
        };
    }

}
