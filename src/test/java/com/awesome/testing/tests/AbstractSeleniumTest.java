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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.net.URL;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({NameLoggerExtension.class, ScreenshotTakerExtension.class})
public abstract class AbstractSeleniumTest {

    protected WebDriver driver;
    protected TestProperties properties;

    @BeforeAll
    static void setupDriver() {
//        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().clearDriverCache().setup();
//        WebDriverManager.edgedriver().clearDriverCache().setup();
    }

    /**
     * @see {https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/events/EventFiringDecorator.html}
     */
    @BeforeEach
    public void setUpDriver() {
        properties = new TestProperties();
        WebDriver original = getDriver();
        TestExecutionListener listener = new TestExecutionListener();
        driver = new EventFiringDecorator<>(listener).decorate(original);
        ScreenshotTakerExtension.setDriver(driver);
    }

    @SneakyThrows
    private WebDriver getDriver() {
        if (properties.useGrid()) {
            String gridUrl = properties.getGridUrl();
            return new RemoteWebDriver(new URL(gridUrl), getCapabilities());
        }

        return switch (properties.getBrowser()) {
            case "chrome" -> getChromeDriver();
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> throw new IllegalStateException("Unsupported browser: " + properties.getBrowser());
        };
    }

    private Capabilities getCapabilities() {
        return switch (properties.getBrowser()) {
            case "chrome" -> new ChromeOptions();
            case "firefox" -> new FirefoxOptions();
            default -> throw new IllegalStateException("Unexpected value: " + properties.getBrowser());
        };
    }

    private ChromeDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        if (properties.isHeadless()) {
            options.addArguments("--headless=new");
        }
        return new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
