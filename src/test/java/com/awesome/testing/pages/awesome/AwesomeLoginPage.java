package com.awesome.testing.pages.awesome;

import com.awesome.testing.components.awesome.AwesomeAlert;
import com.awesome.testing.pages.BasePage;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AwesomeLoginPage extends BasePage {

    private final AwesomeAlert awesomeAlert;

    @FindBy(css = "[name=username]")
    private WebElement usernameField;

    @FindBy(css = "[name=password]")
    private WebElement passwordField;

    @FindBy(css = ".btn-primary")
    private WebElement loginButton;

    @FindBy(css = ".btn-link")
    private WebElement registerLink;

    public AwesomeLoginPage(WebDriver driver) {
        super(driver);
        awesomeAlert = new AwesomeAlert(driver);
    }

    public AwesomeHomePage attemptLogin(String username, String password) {
        return attemptLogin(username, password, AwesomeHomePage.class);
    }

    @SneakyThrows
    public <T extends BasePage> T attemptLogin(String username, String password, Class<T> expectedPage) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return getNewInstance(expectedPage);
    }

    public AwesomeRegisterPage clickRegister() {
        registerLink.click();
        return new AwesomeRegisterPage(driver);
    }

    public AwesomeAlert getAwesomeAlert() {
        return awesomeAlert;
    }
}
