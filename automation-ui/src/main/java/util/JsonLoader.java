package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class JsonLoader {
    private static final String basePath = "/home/yousef/dumy/QA-Testing/automation_exercises/src/main/resources/test-data";
    private static final Logger logger = LoggerFactory.getLogger(JsonLoader.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static <T> T loadConfig(String filePath, Class<T> clazz) {
        try {

            var resilt = MAPPER.readValue(new File(basePath+filePath),clazz);
            logger.info("read credentials  {}", filePath);
            return resilt;
        }
        catch (IOException e) {
            logger.error("Invalid JSON in file: {} Error {}", filePath,e.getMessage());
            throw new IllegalArgumentException();
    }
}}
