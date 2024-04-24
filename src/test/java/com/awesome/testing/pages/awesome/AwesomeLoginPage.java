package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AwesomeLoginPage extends AbstractBasePage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(className = "btn-primary")
    private WebElement loginButton;

    @FindBy(className = "btn-link")
    private WebElement registerButton;

    public AwesomeLoginPage(WebDriver driver) {
        super(driver);
    }

    public <T extends AbstractBasePage> T attemptLogin(String username, String password, Class<T> expectedPage) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return newInstanceOf(expectedPage);
    }

    public AwesomeRegisterPage clickRegister() {
        registerButton.click();
        return new AwesomeRegisterPage(driver);
    }

    public void verifyAlertFailureMessage(String alertMessage) {
        wait.until(driver -> driver.findElement(By.className("alert-danger")).getText().equals(alertMessage));
    }
}
