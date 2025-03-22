package com.awesome.testing.tests;

import com.awesome.testing.extensions.NameLoggerExtension;
import com.awesome.testing.extensions.ScreenshotTakerExtension;
import com.awesome.testing.listeners.TestExecutionListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

@ExtendWith({ScreenshotTakerExtension.class, NameLoggerExtension.class})
public abstract class SeleniumTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver original = new ChromeDriver(options);
        TestExecutionListener listener = new TestExecutionListener();
        driver = new EventFiringDecorator<>(listener).decorate(original);
        ScreenshotTakerExtension.setDriver(driver);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
