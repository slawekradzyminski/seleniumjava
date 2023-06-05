package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AwesomeRegisterPage extends BasePage {

    public AwesomeRegisterPage(WebDriver driver) {
        super(driver);
    }


    public void verifyPageLoaded() {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h2"), "Register"));
    }
}
