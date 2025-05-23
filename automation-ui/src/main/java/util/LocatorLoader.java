package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LocatorLoader {

    public static Map<String, By> loadLocators(String filePath) {
        Map<String, By> locators = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        String basePath = System.getProperty("user.dir")+ "/src/main/configuration";
        try {
            JsonNode root = mapper.readTree(new File(basePath+filePath));

            Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String key = entry.getKey();

                if ("page".equals(key)) {
                    continue;
                }

                JsonNode locatorNode = entry.getValue();
                String type = locatorNode.get("type").asText();
                String value = locatorNode.get("value").asText();

                By by = createBy(type, value);
                locators.put(key, by);
            }

        } catch (IOException e) {
            throw new RuntimeException("Not able to load locators ", e);
        }

        return locators;
    }

    private static By createBy(String type, String value) {
        return switch (type.toLowerCase()) {
            case "id" -> By.id(value);
            case "name" -> By.name(value);
            case "xpath" -> By.xpath(value);
            case "css", "cssselector" -> By.cssSelector(value);
            case "classname" -> By.className(value);
            case "tagname" -> By.tagName(value);
            case "linktext" -> By.linkText(value);
            case "partiallinktext" -> By.partialLinkText(value);
            default -> throw new IllegalArgumentException("Unknown locator type: " + type);
        };
    }

}
