package com.awesome.testing.pages.awesome.components;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AwesomeAlert extends BasePage {

    public AwesomeAlert(WebDriver driver) {
        super(driver);
    }

    public void verifyAlertSuccess(String message) {
        wait.until(ExpectedConditions.textToBe(By.className("alert-success"), message));
    }

    public void verifyAlertFailure(String message) {
        wait.until(ExpectedConditions.textToBe(By.className("alert-danger"), message));
    }

}
