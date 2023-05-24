package com.awesome.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(className = "icon_mail")
    private WebElement messagesIcon;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public MessagesPage openProjects() {
        messagesIcon.click();
        return new MessagesPage(driver);
    }


}
