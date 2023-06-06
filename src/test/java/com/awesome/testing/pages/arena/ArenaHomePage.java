package com.awesome.testing.pages.arena;

import com.awesome.testing.pages.BasePage;
import com.awesome.testing.props.TestProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class ArenaHomePage extends BasePage {

    @FindBy(className = "icon_mail")
    private WebElement messagesIcon;

    @FindBy(css = ".user-info small")
    private WebElement userEmailSection;

    public ArenaHomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open project")
    public ArenaMessagesPage openProjects() {
        messagesIcon.click();
        return new ArenaMessagesPage(driver);
    }

    public void verifyLoginSucceeded() {
        TestProperties testProperties = new TestProperties();
        assertThat(userEmailSection.getText()).isEqualTo(testProperties.getLogin());
    }


}
