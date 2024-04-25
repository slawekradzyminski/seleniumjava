package com.awesome.testing.pages.awesome;

import com.awesome.testing.dto.UserDto;
import com.awesome.testing.exceptions.CouldNotFindUserException;
import com.awesome.testing.pages.AbstractBasePage;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void assertThatAtLeastOneUserIsDisplayed() {
        wait.until(driver -> !driver.findElements(By.cssSelector("li")).isEmpty());
    }

    public AwesomeEditPage clickEditOn(UserDto user) {
        WebElement rowWithMyUser = findRowWithUser(user);

        rowWithMyUser.findElement(By.className("edit")).click();
        return new AwesomeEditPage(driver);
    }

    private WebElement findRowWithUser(UserDto user) {
        return driver.findElements(By.cssSelector("li")).stream()
                .filter(el -> el.getText().contains(String.format("%s %s", user.getFirstName(), user.getLastName())))
                .findFirst()
                .orElseThrow(CouldNotFindUserException::new);
    }
}
