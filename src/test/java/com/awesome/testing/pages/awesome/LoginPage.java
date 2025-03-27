package com.awesome.testing.pages.awesome;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "[type=submit]")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openPage() {
        driver.get(ConfigProvider.get("frontend.url"));
        return this;
    }

    public LoggedInHomePage attemptLogin(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        signInButton.click();
        return new LoggedInHomePage(driver);
    }

}
