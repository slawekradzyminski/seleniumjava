package com.awesome.testing.pages.testarena;

import com.awesome.testing.pages.BasePage;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TAHomePage extends BasePage {

    @FindBy(className = "icon_mail")
    private WebElement messagesIcon;

    public TAHomePage(WebDriver driver) {
        super(driver);
    }

    @SneakyThrows
    public TAMessagesPage openProjects() {
        messagesIcon.click();
        return new TAMessagesPage(driver);
    }


}
