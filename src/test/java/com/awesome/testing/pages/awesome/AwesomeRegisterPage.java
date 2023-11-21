package com.awesome.testing.pages.awesome;

import com.awesome.testing.generators.UserDto;
import com.awesome.testing.pages.BasePage;
import com.awesome.testing.pages.awesome.components.AwesomeAlert;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class AwesomeRegisterPage extends BasePage {

    @Getter
    private final AwesomeAlert alert;

    @FindBy(css = "h2")
    private WebElement header;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(className = "btn-primary")
    private WebElement registerButton;

    public AwesomeRegisterPage(WebDriver driver) {
        super(driver);
        alert = new AwesomeAlert(driver);
    }

    public void verifyHeader() {
        assertThat(header.getText()).isEqualTo("Register");
    }

    public <T extends BasePage> T attemptRegister(UserDto user, Class<T> expectedPage) {
        usernameField.sendKeys(user.getUsername());
        passwordField.sendKeys(user.getPassword());
        emailField.sendKeys(user.getEmail());
        firstNameField.sendKeys(user.getFirstName());
        lastNameField.sendKeys(user.getLastName());
        registerButton.click();
        return newInstanceOf(expectedPage);
    }

}
