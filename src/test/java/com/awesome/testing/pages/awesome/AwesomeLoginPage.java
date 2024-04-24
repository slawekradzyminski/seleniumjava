package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AwesomeLoginPage extends AbstractBasePage {

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(className = "btn-primary")
    private WebElement loginButton;

    public AwesomeLoginPage(WebDriver driver) {
        super(driver);
    }

}
