package com.one.kafka.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one.kafka.api.request.ProductRequest;
import com.one.kafka.broker.message.ProductMessage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PostProduct {
    public void postData(List<ProductRequest> objects, int delayInMillis, int numberOfPosts) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        for (int i = 0; i < Math.min(objects.size(), numberOfPosts); i++) {
            ProductRequest obj = objects.get(i);
            String json = mapper.writeValueAsString(obj);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:9001/api/product_feedback/"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response: " + response.body());

            Thread.sleep(delayInMillis); // Delay between posts
        }
    }
}
