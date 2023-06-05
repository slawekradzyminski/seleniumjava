package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AwesomeHomePage extends BasePage {

    public AwesomeHomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyWelcomeMessage(String expected) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h1"), expected));
    }
}
