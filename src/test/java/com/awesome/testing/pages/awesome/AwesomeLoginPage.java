package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import com.awesome.testing.pages.awesome.components.AwesomeAlert;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AwesomeLoginPage extends BasePage {

    @Getter
    private final AwesomeAlert alert;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(className = "btn-primary")
    private WebElement loginButton;

    @FindBy(className = "btn-link")
    private WebElement registerLink;

    public AwesomeLoginPage(WebDriver driver) {
        super(driver);
        alert = new AwesomeAlert(driver);
    }

    @Step("Login")
    public <T extends BasePage> T attemptLogin(String username, String password, Class<T> expectedPage) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return newInstanceOf(expectedPage);
    }

    public AwesomeRegisterPage clickRegister() {
        registerLink.click();
        return new AwesomeRegisterPage(driver);
    }
}
