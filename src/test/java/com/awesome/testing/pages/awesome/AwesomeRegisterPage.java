package com.awesome.testing.pages.awesome;

import com.awesome.testing.generator.dto.User;
import com.awesome.testing.pages.BasePage;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AwesomeRegisterPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(className = "btn-primary")
    private WebElement registerButton;

    public AwesomeRegisterPage(WebDriver driver) {
        super(driver);
    }

    public void verifyPageLoaded() {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h2"), "Register"));
    }

    public AwesomeLoginPage attemptRegister(User user) {
        return attemptRegister(user, AwesomeLoginPage.class);
    }

    @SneakyThrows
    public <T extends BasePage> T attemptRegister(User user, Class<T> expectedPage) {
        usernameField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        firstNameField.sendKeys(user.getFirstName());
        lastNameField.sendKeys(user.getLastName());
        emailField.sendKeys(user.getEmail());
        registerButton.click();
        return expectedPage.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

    public void verifyErrorMessageContains(String errorMessage) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector(".alert-danger"), errorMessage));
    }
}
