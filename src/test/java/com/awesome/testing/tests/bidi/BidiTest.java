package com.awesome.testing.tests.bidi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BidiTest {

    protected ChromeDriver driver;

    @BeforeEach
    public void setUpDriver() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
