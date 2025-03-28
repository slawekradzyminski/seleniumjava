package com.awesome.testing.tests;

import com.awesome.testing.extensions.AllureLoggingExtension;
import com.awesome.testing.extensions.NameLoggingExtension;
import com.awesome.testing.extensions.ScreenshotTakerExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith({NameLoggingExtension.class, ScreenshotTakerExtension.class, AllureLoggingExtension.class})
public abstract class SeleniumTest {

    protected ChromeDriver driver;

    @BeforeEach
    public void setUpDriver() {
        driver = new ChromeDriver();
        ScreenshotTakerExtension.setDriver(driver);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
