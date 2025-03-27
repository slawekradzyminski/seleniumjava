package com.awesome.testing.pages.awesome;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.pages.BasePage;
import com.awesome.testing.pages.awesome.components.Toast;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @Getter
    private final Toast toast;

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "[type=submit]")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        toast = new Toast(driver);
    }

    public LoginPage openPage() {
        driver.get(ConfigProvider.get("frontend.url"));
        return this;
    }

    public <T extends BasePage> T attemptLogin(String username, String password, Class<T> expectedPage) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInButton.click();
        return getInstance(expectedPage);
    }

    public void verifyLoginUrl() {
        wait.until(ExpectedConditions.urlContains("/login"));
    }

}
