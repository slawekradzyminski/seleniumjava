package com.awesome.testing.tests.w3c.remote;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class RemoteFirefoxTest {

    protected WebDriver driver;

    @BeforeEach
    void setupDriver() throws MalformedURLException {
        driver = RemoteWebDriver.builder()
                .address(new URL("http://localhost:4444/"))
                .addAlternative(getOptions())
                .build();
    }

    private FirefoxOptions getOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("fission.bfcacheInParent", false);
        options.addPreference("fission.webContentIsolationStrategy", 0);
        options.setCapability("webSocketUrl", true);
        return options;
    }

    @AfterEach
    public void cleanup() {
        driver.quit();
    }

}
