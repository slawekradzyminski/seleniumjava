package com.awesome.testing.pages.awesome;

import com.awesome.testing.components.awesome.AwesomeAlert;
import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AwesomeHomePage extends BasePage {

    private final AwesomeAlert awesomeAlert;

    @FindBy(css = "li")
    private List<WebElement> userRows;

    @FindBy(id = "logout")
    private WebElement logoutLink;

    public AwesomeHomePage(WebDriver driver) {
        super(driver);
        awesomeAlert = new AwesomeAlert(driver);
    }

    public void verifyWelcomeMessage(String expected) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h1"), expected));
    }

    public void verifyUsersDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul li")));
    }

    public AwesomeLoginPage clickLogout() {
        logoutLink.click();
        return new AwesomeLoginPage(driver);
    }

    public AwesomeEditPage clickEditOnLastUser() {
        List<WebElement> editLinks = driver.findElements(By.cssSelector(".edit"));
        editLinks.get(editLinks.size() - 1).click();
        return new AwesomeEditPage(driver);
    }

    public AwesomeAlert getAlert() {
        return awesomeAlert;
    }
}
