package com.awesome.testing.pages.awesome;

import com.awesome.testing.generators.UserDto;
import com.awesome.testing.pages.BasePage;
import com.awesome.testing.pages.awesome.components.AwesomeAlert;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AwesomeHomePage extends BasePage {

    @Getter
    private AwesomeAlert alert;

    @FindBy(css = "li")
    private List<WebElement> userRows;

    public AwesomeHomePage(WebDriver driver) {
        super(driver);
        alert = new AwesomeAlert(driver);
    }

    @Step("Header verification")
    public void verifyHeaderForName(String name) {
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h1"), getWelcomeMessageFor(name)));
    }

    private String getWelcomeMessageFor(String name) {
        return String.format("Hi %s!", name);
    }

    public AwesomeEditPage clickEditOnSpecificUser(UserDto user) {
        WebElement editButton = userRows.stream()
                .filter(row -> row.getText().contains(userData(user)))
                .findFirst()
                .map(el -> el.findElement(By.className("edit")))
                .orElseThrow();
        editButton.click();

        return new AwesomeEditPage(driver);
    }

    private String userData(UserDto user) {
        return String.format("%s %s", user.getFirstName(), user.getLastName());
    }
}
