package com.awesome.testing.props;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class TestProperties {

    private Properties properties;

    @SneakyThrows
    public TestProperties () {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "test.properties";
        properties = new Properties();
        properties.load(new FileInputStream(appConfigPath));
    }

    public String getLogin() {
        return properties.getProperty("login");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

}
