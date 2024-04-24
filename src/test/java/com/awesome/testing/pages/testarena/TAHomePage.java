package com.awesome.testing.pages.testarena;

import com.awesome.testing.pages.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TAHomePage extends AbstractBasePage {

    @FindBy(className = "icon_mail")
    private WebElement messagesIcon;

    public TAHomePage(WebDriver driver) {
        super(driver);
    }

    public TAMessagesPage openProjects() {
        messagesIcon.click();
        return new TAMessagesPage(driver);
    }


}
