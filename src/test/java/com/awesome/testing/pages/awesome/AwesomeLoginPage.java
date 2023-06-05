package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AwesomeLoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(className = "btn-primary")
    private WebElement loginButton;

    public AwesomeLoginPage(WebDriver driver) {
        super(driver);
    }

    public AwesomeHomePage attemptLogin(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return new AwesomeHomePage(driver);
    }
}
