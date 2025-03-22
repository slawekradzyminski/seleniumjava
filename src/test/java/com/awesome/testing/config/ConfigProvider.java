package com.awesome.testing.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class ConfigProvider {

    private static final Properties props = new Properties();

    static {
        try (InputStream inputStream =
                     ConfigProvider.class.getResourceAsStream("/config.properties")) {
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    public static String get(String key) {
        return Optional.ofNullable(System.getProperty(key))
                .orElse(props.getProperty(key));
    }
}

