package com.awesome.testing.components.awesome;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AwesomeAlert extends BasePage {

    public AwesomeAlert(WebDriver driver) {
        super(driver);
    }

    public void verifyErrorMessage(String errorMessage) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector(".alert-danger"), errorMessage));
    }

    public void verifySuccessMessage(String message) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector(".alert-success"), message));
    }

}
