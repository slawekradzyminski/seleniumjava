package com.awesome.testing.components;

import com.awesome.testing.pages.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertComponent extends AbstractBasePage {

    public AlertComponent(WebDriver driver) {
        super(driver);
    }

    public void verifyAlertFailureMessage(String alertMessage) {
        wait.until(driver -> driver.findElement(By.className("alert-danger")).getText().equals(alertMessage));
    }

    public void verifyAlertSuccessMessage(String alertMessage) {
        wait.until(driver -> driver.findElement(By.className("alert-success")).getText().equals(alertMessage));
    }

}
