package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class AwesomeLoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
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

    public void verifyErrorMessage() {
        assertThat(getAlert().getText()).contains("Invalid username/password supplied");
    }

    public void verifyRegistrationSuccessfulMessage() {
        assertThat(getAlert().getText()).contains("Registration successful");
    }

    private WebElement getAlert() {
        By alertBy = By.cssSelector(".alert");
        wait.until(ExpectedConditions.visibilityOfElementLocated(alertBy));
        return driver.findElement(alertBy);
    }

    public AwesomeRegisterPage clickRegister() {
        registerLink.click();
        return new AwesomeRegisterPage(driver);
    }
}
