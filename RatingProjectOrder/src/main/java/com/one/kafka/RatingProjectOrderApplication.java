package com.one.kafka;

import com.one.kafka.api.request.ProductRequest;
import com.one.kafka.api.server.ProductRequestInitiator;
import com.one.kafka.broker.message.ProductMessage;
import com.one.kafka.config.JsonReader;
import com.one.kafka.network.PostProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RatingProjectOrderApplication implements CommandLineRunner {

//	@Autowired
//	private ProductRequestInitiator productRequestInitiator;
//
//	private static final int DELAY = 1000;
//	private static final int NUMBER_OF_PRODUCT = 5;

	public static void main(String[] args) {
		SpringApplication.run(RatingProjectOrderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		productRequestInitiator.createAndSendProductFeedback(NUMBER_OF_PRODUCT,DELAY);
	}
}
