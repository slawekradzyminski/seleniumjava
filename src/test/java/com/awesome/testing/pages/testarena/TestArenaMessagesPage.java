package com.awesome.testing.pages.testarena;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestArenaMessagesPage extends BasePage {

    @FindBy(id = "j_msgContent")
    private WebElement messageTextArea;

    protected TestArenaMessagesPage(WebDriver driver) {
        super(driver);
    }

    public void waitForTextAreaToLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(messageTextArea));
    }

}
