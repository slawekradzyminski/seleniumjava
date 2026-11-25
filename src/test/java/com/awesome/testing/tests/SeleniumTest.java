package com.awesome.testing.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class SeleniumTest {

    protected ChromeDriver driver;

    @BeforeEach
    public void setUpDriver() {
        ChromeOptions options = new ChromeOptions();
        // Recent Chrome upgrades/blocks plain HTTP by default; demo.testarena.pl is HTTP-only
        options.addArguments("--disable-features=HttpsUpgrades,HttpsFirstBalancedModeAutoEnable,HttpsFirstModeV2ForTypicallySecureUsers");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

}
