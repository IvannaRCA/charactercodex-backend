package com.ivanna.charactercodex.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.type.CollectionType;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedJsonReader {
    private final ObjectMapper objectMapper;

    public <T> List<T> readList(String fileName, Class<T> dtoClass){
        String path = "seed/" + fileName;

        try (InputStream inputStream = new ClassPathResource(path).getInputStream()) {
            CollectionType listType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, dtoClass);
            return objectMapper.readValue(inputStream, listType);
        } catch (IOException e) {
            log.error("Failed to read seed file: {}", path, e);
            throw new IllegalStateException("Could not load seed data from " + path, e);
        }
    }
}
