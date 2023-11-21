package com.awesome.testing.properties;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class TestProperties {

    private final Properties properties;

    @SneakyThrows
    public TestProperties() {
        String path = getPropertiesPath();
        properties = new Properties();
        properties.load(new FileInputStream(path));
    }

    @SuppressWarnings("ConstantConditions")
    private String getPropertiesPath() {
        return Thread.currentThread().getContextClassLoader().getResource("").getPath() + "test.properties";
    }

    public String getLogin() {
        return getStringProperty("login");
    }

    public String getPassword() {
        return getStringProperty("password");
    }

    public String getBrowser() {
        return getStringPropertyOrDefault("browser", "chrome");
    }

    public String getUrl() {
        return getStringProperty("url");
    }

    private String getStringProperty(String propertyName) {
        return System.getProperty(propertyName, properties.getProperty(propertyName));
    }

    private String getStringPropertyOrDefault(String propertyName, String defaultValue) {
        return System.getProperty(propertyName, properties.getProperty(propertyName, defaultValue));
    }

}