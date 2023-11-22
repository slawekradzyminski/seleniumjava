package com.awesome.testing.tests;

import com.awesome.testing.extensions.NameLoggerExtension;
import com.awesome.testing.extensions.ScreenshotTakerExtension;
import com.awesome.testing.listeners.TestExecutionListener;
import com.awesome.testing.properties.TestProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

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
        WebDriver original = getBrowser();
        TestExecutionListener listener = new TestExecutionListener();
        driver = new EventFiringDecorator<>(listener).decorate(original);
        ScreenshotTakerExtension.setDriver(driver);
    }

    private WebDriver getBrowser() {
        return switch (testProperties.getBrowser()) {
            case "chrome" -> new ChromeDriver();
            case "edge" -> new EdgeDriver();
            case "firefox" -> new FirefoxDriver();
            case "safari" -> new SafariDriver();
            default -> throw new IllegalStateException("Unexpected value: " + testProperties.getBrowser());
        };
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
