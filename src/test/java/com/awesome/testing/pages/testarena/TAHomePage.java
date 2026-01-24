package com.awesome.testing.pages.testarena;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TAHomePage extends BasePage {

    @FindBy(css = "a.top_messages")
    private WebElement messagesLink;

    public TAHomePage(WebDriver driver) {
        super(driver);
    }

    public TAMessagesPage openProjects() {
        wait.until(ExpectedConditions.elementToBeClickable(messagesLink)).click();
        return new TAMessagesPage(driver);
    }


}
