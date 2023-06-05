package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AwesomeLoginPage extends BasePage {

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
    }

    public AwesomeHomePage attemptLogin(String username, String password) {
        return attemptLogin(username, password, AwesomeHomePage.class);
    }

    @SneakyThrows
    public <T extends BasePage> T attemptLogin(String username, String password, Class<T> expectedPage) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return expectedPage.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

    public void verifyErrorMessageContains(String errorMessage) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector(".alert-danger"), errorMessage));
    }

    public AwesomeRegisterPage clickRegister() {
        registerLink.click();
        return new AwesomeRegisterPage(driver);
    }
}
