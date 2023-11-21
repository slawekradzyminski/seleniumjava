package com.awesome.testing.pages.testarena;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestArenaHomePage extends BasePage {

    @FindBy(className = "icon_mail")
    private WebElement messagesIcon;

    public TestArenaHomePage(WebDriver driver) {
        super(driver);
    }

    public TestArenaMessagesPage openMessagesPage() {
        messagesIcon.click();
        return new TestArenaMessagesPage(driver);
    }


}
