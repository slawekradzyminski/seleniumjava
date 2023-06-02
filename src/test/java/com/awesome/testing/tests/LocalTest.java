package com.awesome.testing.tests;

import com.awesome.testing.extension.Screenshotter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(Screenshotter.class)
public abstract class LocalTest {

    protected ChromeDriver driver;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriver() {
        driver = new ChromeDriver();
        Screenshotter.setDriver(driver);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
