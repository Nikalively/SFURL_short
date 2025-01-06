package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private final Properties properties = new Properties();

    public Settings(String path) {
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}