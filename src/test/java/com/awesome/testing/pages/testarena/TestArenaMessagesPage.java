package com.awesome.testing.pages.testarena;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TestArenaMessagesPage extends BasePage {

    @FindBy(id = "j_msgContent")
    private WebElement messageTextArea;

    @FindBy(id = "j_msgResponse-193")
    private WebElement replyButton;

    protected TestArenaMessagesPage(WebDriver driver) {
        super(driver);
    }

    public TestArenaMessagesPage waitForTextAreaToLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(messageTextArea));
        return this;
    }

    public TestArenaMessagesPage addMessage(String message) {
        messageTextArea.sendKeys(message);
        replyButton.click();
        return this;
    }

    public void verifyMessagePresent(String message) {
        wait.until(driver -> lastElementContains(message, driver));
    }

    private boolean lastElementContains(String message, WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.className("message_content_text"));
        return elements.get(elements.size() - 1).getText().equals(message);
    }

}
