package com.awesome.testing.pages.arena;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArenaHomePage extends BasePage {

    @FindBy(className = "icon_mail")
    private WebElement messagesIcon;

    public ArenaHomePage(WebDriver driver) {
        super(driver);
    }

    public ArenaMessagesPage openProjects() {
        messagesIcon.click();
        return new ArenaMessagesPage(driver);
    }


}
