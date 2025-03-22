package com.awesome.testing.tests.bidi.cdp.remote;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class RemoteTest {

    protected WebDriver driver;

    @BeforeEach
    void setupDriver() throws MalformedURLException, URISyntaxException {
        URI gridUri = new URI("http://localhost:4444/");
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(gridUri.toURL(), options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
