package com.awesome.testing.tests;

import com.awesome.testing.extensions.ListenerAsClass;
import com.awesome.testing.props.TestProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public abstract class SeleniumTest {

    protected WebDriver driver;
    protected TestProperties testProperties;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriver() {
        WebDriver original = new ChromeDriver();
        driver = new EventFiringDecorator<>(new ListenerAsClass()).decorate(original);
        testProperties = new TestProperties();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
