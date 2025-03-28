package com.awesome.testing.tests;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.extensions.NameLoggingExtension;
import com.awesome.testing.extensions.ScreenshotTakerExtension;
import com.awesome.testing.listeners.TestExecutionListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.net.URL;

@Execution(ExecutionMode.CONCURRENT)
@Slf4j
@ExtendWith({NameLoggingExtension.class, ScreenshotTakerExtension.class})
public abstract class SeleniumTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUpDriver() {
        WebDriver original = getRemoteWebDriver();
        WebDriverListener listener = new TestExecutionListener();
        driver = new EventFiringDecorator<>(listener).decorate(original);
        ScreenshotTakerExtension.setDriver(driver);
    }

    @SneakyThrows
    private WebDriver getRemoteWebDriver() {
        String gridUrl = "http://localhost:4444";
        return new RemoteWebDriver(new URL(gridUrl), new ChromeOptions());
    }

    private WebDriver setupAppropriateDriver() {
        switch (ConfigProvider.get("browser")) {
            case "edge" -> {
                log.info("Using Edge");
                return new EdgeDriver();
            }
            case "firefox" -> {
                log.info("Using Firefox");
                return new FirefoxDriver();
            }
            default -> {
                log.info("Using Chrome");
                return new ChromeDriver();
            }
        }
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
