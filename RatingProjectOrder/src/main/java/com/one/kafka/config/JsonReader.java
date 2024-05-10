package com.one.kafka.config;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.one.kafka.api.request.ProductRequest;
import com.one.kafka.broker.message.ProductMessage;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class JsonReader {
    public List<ProductRequest> readJsonFile(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource(filePath);
        if (!resource.exists()) {
            throw new IllegalStateException("File not found in classpath: " + filePath);
        }
        InputStream inputStream = resource.getInputStream();
        return mapper.readValue(inputStream, new TypeReference<List<ProductRequest>>(){});
    }
}
