package com.awesome.testing.pages.awesome.components;

import com.awesome.testing.pages.BasePage;
import com.awesome.testing.pages.awesome.LoginPage;
import com.awesome.testing.pages.awesome.ProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoggedInHeader extends BasePage {

    public LoggedInHeader(WebDriver driver) {
        super(driver);
    }

    public ProfilePage clickOnName(String firstName, String lastName) {
        String name = String.format("%s %s", firstName, lastName);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(name)));
        driver.findElement(By.linkText(name)).click();
        return new ProfilePage(driver);
    }

    public LoginPage clickLogout() {
        wait.until(driver -> driver.findElements(By.cssSelector("nav button")).size() == 2);
        driver.findElements(By.cssSelector("nav button")).getFirst().click();
        return new LoginPage(driver);
    }

    public <T extends BasePage> T clickOnLink(String link, Class<T> expectedPage) {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(link)));
        driver.findElement(By.linkText(link)).click();
        return getInstance(expectedPage);
    }
}
