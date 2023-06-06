package com.awesome.testing.pages.arena;

import com.awesome.testing.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ArenaMessagesPage extends BasePage {

    @FindBy(id = "j_msgContent")
    private WebElement messageTextArea;

    protected ArenaMessagesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Wait for load")
    public void waitForTextAreaToLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(messageTextArea));
    }

}
