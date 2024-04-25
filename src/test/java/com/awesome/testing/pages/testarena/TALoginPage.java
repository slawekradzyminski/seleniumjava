package com.awesome.testing.pages.testarena;

import com.awesome.testing.pages.AbstractBasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TALoginPage extends AbstractBasePage {

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    public TALoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Login to Test Arena")
    public TAHomePage attemptLogin(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
        return new TAHomePage(driver);
    }
}
