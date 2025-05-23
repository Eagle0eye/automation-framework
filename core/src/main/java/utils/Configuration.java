package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Configuration.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.err.println("Sorry, unable to find config.properties");
                throw new RuntimeException("config.properties not found in classpath");
            }
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load config.properties", ex);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
