package com.awesome.testing.tests.w3c.local;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public abstract class LocalFirefoxTest {

    protected FirefoxDriver driver;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("webSocketUrl", true);
        driver = new FirefoxDriver(options);
    }

    @AfterEach
    public void cleanup() {
        driver.quit();
    }

}
