package com.awesome.testing.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class ConfigProvider {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigProvider.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties in resources");
            }
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading config.properties", ex);
        }
    }

    public static String get(String name) {
        return Optional.ofNullable(System.getProperty(name))
                .orElse(props.getProperty(name));
    }

}
