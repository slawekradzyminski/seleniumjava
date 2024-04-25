package com.awesome.testing.pages.awesome;

import com.awesome.testing.dto.UserDto;
import com.awesome.testing.exceptions.CouldNotFindUserException;
import com.awesome.testing.pages.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AwesomeHomePage extends AbstractBasePage {

    @FindBy(css = "h1")
    private WebElement header;

    public AwesomeHomePage(WebDriver driver) {
        super(driver);
    }

    public void assertThatHeaderContains(String text) {
        wait.until((driver) -> header.getText().contains(text));
    }

    public AwesomeHomePage assertThatAtLeastOneUserIsDisplayed() {
        wait.until(driver -> !getUsers().isEmpty());
        return this;
    }

    public AwesomeEditPage clickEditOn(UserDto user) {
        WebElement rowWithMyUser = findRowWithUser(user);

        rowWithMyUser.findElement(By.className("edit")).click();
        return new AwesomeEditPage(driver);
    }

    private WebElement findRowWithUser(UserDto user) {
        return getUsers().stream()
                .filter(el -> el.getText().contains(String.format("%s %s", user.getFirstName(), user.getLastName())))
                .findFirst()
                .orElseThrow(CouldNotFindUserException::new);
    }

    public void deleteThirdUser() {
        String thirdUser = getUsers().get(2).getText();

        getUsers().get(2).findElement(By.className("delete")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        wait.until((driver) -> {
            if (getUsers().size() < 3) return true;
            return !getUsers().get(2).getText().equals(thirdUser);
        });
    }

    private List<WebElement> getUsers() {
        return driver.findElements(By.cssSelector("li"));
    }
}
