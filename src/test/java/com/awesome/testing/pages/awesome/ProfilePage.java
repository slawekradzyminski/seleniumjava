package com.awesome.testing.pages.awesome;

import com.awesome.testing.http.dto.RegisterRequestDto;
import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void verifyPersonalInformation(RegisterRequestDto user) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        assertThat(driver.findElement(By.id("email")).getDomProperty("value")).isEqualTo(user.getEmail());
        assertThat(driver.findElement(By.id("firstName")).getDomProperty("value")).isEqualTo(user.getFirstName());
        assertThat(driver.findElement(By.id("lastName")).getDomProperty("value")).isEqualTo(user.getLastName());
    }

}
