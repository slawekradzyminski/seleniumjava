package com.awesome.testing.tests.awesome;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.http.LoginApi;
import com.awesome.testing.http.RegisterApi;
import com.awesome.testing.http.dto.RegisterRequestDto;
import com.awesome.testing.pages.awesome.LoggedInHomePage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generators.UserGenerator.getRandomUser;

public class LoggedInHomePageTest extends SeleniumTest {

    String token;
    RegisterRequestDto user;

    @BeforeEach
    public void setUp() {
        user = getRandomUser();
        RegisterApi.register(user);
        token = LoginApi.login(user.getUsername(), user.getPassword());

        driver.get(ConfigProvider.get("frontend.url"));
        driver.executeScript("window.localStorage.setItem('token', arguments[0]);", token);
        driver.navigate().to(ConfigProvider.get("frontend.url"));
    }

    @Test
    public void shouldLogOutSuccessfully() {
        new LoggedInHomePage(driver)
                .getLoggedInHeader()
                .clickLogout()
                .verifyLoginUrl();
    }

    @Test
    public void shouldOpenProfilePage() {
        new LoggedInHomePage(driver)
                .getLoggedInHeader()
                .clickOnName(user.getFirstName(), user.getLastName())
                .verifyPersonalInformation(user);
    }
}
