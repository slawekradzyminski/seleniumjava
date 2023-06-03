package com.awesome.testing.pages.awesome;

import com.awesome.testing.generator.User;
import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class AwesomeRegisterPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(css = ".btn-primary")
    private WebElement registerButton;

    @FindBy(css = "h2")
    private WebElement header;

    public AwesomeRegisterPage(WebDriver driver) {
        super(driver);
    }

    public void isAt() {
        assertThat(header.getText()).isEqualTo("Register");
    }

    public AwesomeLoginPage attemptRegister(User user) {
        usernameField.sendKeys(user.getFirstName());
        passwordField.sendKeys(user.getPassword());
        firstNameField.sendKeys(user.getFirstName());
        lastNameField.sendKeys(user.getLastName());
        emailField.sendKeys(user.getEmail());
        registerButton.click();

        return new AwesomeLoginPage(driver);
    }
}
