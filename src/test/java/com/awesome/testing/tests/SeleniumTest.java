package com.awesome.testing.tests;

import com.awesome.testing.extension.Screenshotter;
import com.awesome.testing.props.TestProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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

import java.net.MalformedURLException;
import java.net.URL;

import static com.awesome.testing.listener.SeleniumListener.LISTENER;

@Execution(ExecutionMode.CONCURRENT)
@Slf4j
@ExtendWith(Screenshotter.class)
public abstract class SeleniumTest {

    protected WebDriver driver;
    protected TestProperties testProperties;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    /**
     * https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/events/EventFiringDecorator.html
     */
    @SneakyThrows
    @BeforeEach
    public void setUpDriver() {
        testProperties = new TestProperties();
        WebDriver original = resolveDriver();
        driver = new EventFiringDecorator<>(LISTENER).decorate(original);
        Screenshotter.setDriver(driver);
    }

    private WebDriver resolveDriver() throws MalformedURLException {
        if (testProperties.useGrid()) {
            URL gridUrl = new URL("http://localhost:4444/");
            ChromeOptions options = new ChromeOptions();
            return new RemoteWebDriver(gridUrl, options);
        } else {
            return new ChromeDriver();
        }
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
