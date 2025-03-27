package com.awesome.testing.pages.awesome;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.http.dto.RegisterRequestDto;
import com.awesome.testing.pages.BasePage;
import com.awesome.testing.pages.awesome.components.Toast;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;
    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(name = "firstName")
    private WebElement firstNameInput;
    @FindBy(name = "lastName")
    private WebElement lastNameInput;
    @FindBy(css = "[type=submit]")
    private WebElement createAccountButton;

    @Getter
    private final Toast toast;

    public RegisterPage(WebDriver driver) {
        super(driver);
        toast = new Toast(driver);
    }

    public RegisterPage openPage() {
        driver.get(ConfigProvider.get("frontend.url") + "/register");
        return this;
    }

    public <T extends BasePage> T attemptRegister(RegisterRequestDto user, Class<T> expectedPage) {
        usernameInput.sendKeys(user.getUsername());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        createAccountButton.click();

        return getInstance(expectedPage);
    }
}
