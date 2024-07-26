package com.javarush.baliuk.islandofanimals.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReaderJacksonFile {
    private String filePath = "src/main/resources/param.json";
    private volatile JsonNode jsonNode;
    private ReaderJacksonFile() {
    }
    private static class SingletonHelper {
        private static final ReaderJacksonFile INSTANCE = new ReaderJacksonFile();
    }
    public static ReaderJacksonFile getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public JsonNode mapperJS() throws IOException {
        if (jsonNode == null) {
            synchronized (ReaderJacksonFile.class) {
                if (jsonNode == null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    byte[] jsonString = Files.readAllBytes(Path.of(filePath));
                    jsonNode = objectMapper.readTree(jsonString);
                }
            }
        }
        return jsonNode;
    }
}