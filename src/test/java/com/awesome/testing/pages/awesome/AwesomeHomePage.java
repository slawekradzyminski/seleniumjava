package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AwesomeHomePage extends BasePage {

    public AwesomeHomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyHeaderForName(String name) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h1"), getWelcomeMessageFor(name)));
    }

    private String getWelcomeMessageFor(String name) {
        return String.format("Hi %s!", name);
    }
}
