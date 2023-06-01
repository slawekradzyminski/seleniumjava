package com.awesome.testing.pages.testarena;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MessagesPage extends BasePage {

    @FindBy(id = "j_msgContent")
    private WebElement messageTextArea;

    protected MessagesPage(WebDriver driver) {
        super(driver);
    }

    public void waitForTestAreaToLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(messageTextArea));
    }

}
