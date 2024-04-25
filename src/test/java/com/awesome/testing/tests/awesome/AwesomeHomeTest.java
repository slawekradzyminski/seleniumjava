package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.LoginApi;
import com.awesome.testing.api.RegisterApi;
import com.awesome.testing.dto.LoginResponseDto;
import com.awesome.testing.dto.UserDto;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import com.awesome.testing.tests.AbstractSeleniumTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static com.awesome.testing.generators.UserGenerator.getRandomUser;

public class AwesomeHomeTest extends AbstractSeleniumTest {

    private AwesomeHomePage awesomeHomePage;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        UserDto user = getRandomUser();
        RegisterApi.postSignUp(user);
        driver.get(properties.getUrl());
        LoginResponseDto loginResponseDto = LoginApi.signIn(user.getUsername(), user.getPassword());
        ((JavascriptExecutor)driver).executeScript(String.format("window.localStorage.setItem('%s','%s')",
                "user", new ObjectMapper().writeValueAsString(loginResponseDto)));
        driver.get(properties.getUrl());
        awesomeHomePage = new AwesomeHomePage(driver);
    }

    @Test
    public void shouldDisplayAtLeastOneUser() {
        awesomeHomePage.assertThatAtLeastOneUserIsDisplayed();
    }


}
