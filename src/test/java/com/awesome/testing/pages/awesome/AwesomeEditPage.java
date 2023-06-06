package com.awesome.testing.pages.awesome;

import com.awesome.testing.generator.dto.User;
import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AwesomeEditPage extends BasePage {

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(className = "btn-primary")
    private WebElement editButton;

    public AwesomeEditPage(WebDriver driver) {
        super(driver);
    }

    public AwesomeHomePage editUser(User newUser) {
        firstNameField.clear();
        firstNameField.sendKeys(newUser.getFirstName());
        lastNameField.clear();
        lastNameField.sendKeys(newUser.getLastName());
        emailField.clear();
        emailField.sendKeys(newUser.getEmail());
        editButton.click();
        return new AwesomeHomePage(driver);
    }

    public String readUsername() {
        return usernameField.getAttribute("value");
    }
}
