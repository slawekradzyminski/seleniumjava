package com.awesome.testing.pages.arena;

import com.awesome.testing.pages.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArenaLoginPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    public ArenaLoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Login")
    public ArenaHomePage attemptLogin(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
        return new ArenaHomePage(driver);
    }
}
