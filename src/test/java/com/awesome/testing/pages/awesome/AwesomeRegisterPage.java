package com.awesome.testing.pages.awesome;

import com.awesome.testing.components.AlertComponent;
import com.awesome.testing.dto.UserDto;
import com.awesome.testing.pages.AbstractBasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AwesomeRegisterPage extends AbstractBasePage {

    @Getter
    private AlertComponent alertComponent;

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
        alertComponent = new AlertComponent(driver);
    }

    public void assertHeader() {
        wait.until((driver) -> header.getText().contains("Register"));
    }

    public <T extends AbstractBasePage> T attemptRegister(UserDto userDto, Class<T> expectedPage) {
        firstNameField.sendKeys(userDto.getFirstName());
        lastNameField.sendKeys(userDto.getLastName());
        usernameField.sendKeys(userDto.getUsername());
        passwordField.sendKeys(userDto.getPassword());
        emailField.sendKeys(userDto.getEmail());
        registerButton.click();

        return newInstanceOf(expectedPage);
    }

}
