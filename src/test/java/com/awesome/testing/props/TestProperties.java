package com.awesome.testing.props;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class TestProperties {

    private final Properties properties;

    @SneakyThrows
    public TestProperties() {
        String appConfigPath = getPathToTestProperties();
        properties = new Properties();
        properties.load(new FileInputStream(appConfigPath));
    }

    @SuppressWarnings("ConstantConditions")
    private String getPathToTestProperties() {
        return Thread.currentThread().getContextClassLoader().getResource("").getPath() + "test.properties";
    }

    public String getLogin() {
        return properties.getProperty("login", "admin");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public boolean useGrid() {
        return Boolean.parseBoolean(properties.getProperty("useGrid"));
    }

}
