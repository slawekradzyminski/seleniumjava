package com.awesome.testing.tests;

import com.awesome.testing.extensions.NameLoggerExtension;
import com.awesome.testing.extensions.ScreenshotTakerExtension;
import com.awesome.testing.listeners.TestExecutionListener;
import com.awesome.testing.properties.TestProperties;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.net.URL;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({NameLoggerExtension.class, ScreenshotTakerExtension.class})
public abstract class SeleniumTest {

    protected WebDriver driver;
    protected TestProperties testProperties;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpTest() {
        testProperties = new TestProperties();
        WebDriver original = getDriver();
        TestExecutionListener listener = new TestExecutionListener();
        driver = new EventFiringDecorator<>(listener).decorate(original);
        ScreenshotTakerExtension.setDriver(driver);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver getDriver() {
        if (testProperties.useGrid()) {
            return getRemoteDriver();
        }
        return getLocalDriver();
    }

    @NotNull
    private WebDriver getLocalDriver() {
        return switch (testProperties.getBrowser()) {
            case "chrome" -> getChromeDriver();
            case "edge" -> new EdgeDriver();
            case "firefox" -> new FirefoxDriver();
            case "safari" -> new SafariDriver();
            default -> throw new IllegalStateException("Unexpected value: " + testProperties.getBrowser());
        };
    }

    @SneakyThrows
    private WebDriver getRemoteDriver() {
        URL gridUrl = new URL(testProperties.getGridUrl());
        return new RemoteWebDriver(gridUrl, getCapabilities());
    }

    private Capabilities getCapabilities() {
        return switch (testProperties.getBrowser()) {
            case "chrome" -> new ChromeOptions();
            case "edge" -> new EdgeOptions();
            case "firefox" -> new FirefoxOptions();
            case "safari" -> new SafariOptions();
            default -> throw new IllegalStateException("Unexpected value: " + testProperties.getBrowser());
        };
    }

    private ChromeDriver getChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (testProperties.headless()) {
            chromeOptions.addArguments("--headless=new");
        }
        return new ChromeDriver(chromeOptions);
    }

}
