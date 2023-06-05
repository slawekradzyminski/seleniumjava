package com.awesome.testing.tests;

import com.awesome.testing.props.TestProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class SeleniumTest {

    protected ChromeDriver driver;
    protected TestProperties testProperties;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriver() {
        driver = new ChromeDriver();
        testProperties = new TestProperties();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
