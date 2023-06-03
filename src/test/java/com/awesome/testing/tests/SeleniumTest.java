package com.awesome.testing.tests;

import com.awesome.testing.props.TestProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import static com.awesome.testing.listener.SeleniumListener.LISTENER;

@Slf4j
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
    @BeforeEach
    public void setUpDriver() {
        WebDriver original = new ChromeDriver();
        driver = new EventFiringDecorator<>(LISTENER).decorate(original);
        testProperties = new TestProperties();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
