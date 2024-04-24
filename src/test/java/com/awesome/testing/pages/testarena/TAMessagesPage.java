package com.awesome.testing.pages.testarena;

import com.awesome.testing.pages.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TAMessagesPage extends AbstractBasePage {

    @FindBy(id = "j_msgContent")
    private WebElement messageTextArea;

    @FindBy(className = "j_msgResponse")
    private WebElement responseButton;

    protected TAMessagesPage(WebDriver driver) {
        super(driver);
    }

    public TAMessagesPage waitForTextAreaToLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(messageTextArea));
        return this;
    }

    public TAMessagesPage addMessage(String message) {
        messageTextArea.sendKeys(message);
        responseButton.click();
        return this;
    }

    public void verifyLastMessageIs(String message) {

    }
}
