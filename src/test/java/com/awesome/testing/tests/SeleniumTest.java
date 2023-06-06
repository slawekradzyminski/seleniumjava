package com.awesome.testing.tests;

import com.awesome.testing.extensions.ListenerAsClass;
import com.awesome.testing.extensions.NameLoggerExtension;
import com.awesome.testing.extensions.ScreenshotTaker;
import com.awesome.testing.props.TestProperties;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.net.URL;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({NameLoggerExtension.class, ScreenshotTaker.class})
public abstract class SeleniumTest {

    protected WebDriver driver;
    protected TestProperties testProperties;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriver() {
        testProperties = new TestProperties();
        WebDriver original = getDriver();
        driver = new EventFiringDecorator<>(new ListenerAsClass()).decorate(original);
        ScreenshotTaker.setDriver(driver);
    }

    @SneakyThrows
    @NotNull
    private WebDriver getDriver() {
        if (testProperties.useGrid()) {
            URL gridUrl = new URL("http://localhost:4444/");
            ChromeOptions chromeOptions = new ChromeOptions();
            return new RemoteWebDriver(gridUrl, chromeOptions);
        }
        return new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
