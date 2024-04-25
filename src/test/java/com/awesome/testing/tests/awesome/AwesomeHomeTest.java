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
import org.openqa.selenium.html5.WebStorage;

import static com.awesome.testing.generators.UserGenerator.getRandomUser;

public class AwesomeHomeTest extends AbstractSeleniumTest {

    private AwesomeHomePage awesomeHomePage;
    private final RegisterApi registerApi = new RegisterApi();
    private final LoginApi loginApi = new LoginApi();

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        UserDto user = getRandomUser();
        registerApi.postSignUp(user);
        driver.get(properties.getUrl());
        LoginResponseDto loginResponseDto = loginApi.signIn(user.getUsername(), user.getPassword());
        ((WebStorage) driver).getLocalStorage().setItem("user", new ObjectMapper().writeValueAsString(loginResponseDto));
        driver.get(properties.getUrl());
        awesomeHomePage = new AwesomeHomePage(driver);
    }

    @Test
    public void shouldDisplayAtLeastOneUser() {
        awesomeHomePage.assertThatAtLeastOneUserIsDisplayed();
    }


}
