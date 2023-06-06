package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.LoginApi;
import com.awesome.testing.api.RegisterApi;
import com.awesome.testing.api.dto.LoginResponseDto;
import com.awesome.testing.generator.dto.User;
import com.awesome.testing.tests.SeleniumTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.html5.WebStorage;

import static com.awesome.testing.generator.UserProvider.getRandomUser;

public abstract class AbstractAwesomeLoggedInTest extends SeleniumTest {

    private final LoginApi loginApi = new LoginApi();
    private final RegisterApi registerApi = new RegisterApi();
    protected String token;
    protected User user;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        driver.get(testProperties.getBaseUrl());
        user = getRandomUser();
        registerApi.register(user);
        LoginResponseDto loginResponseDto = loginApi.login(user.getUsername(), user.getPassword());
        token = loginResponseDto.getToken();
        driver.manage().addCookie(new Cookie("token", token));
        ((WebStorage) driver).getLocalStorage().setItem("user", new ObjectMapper().writeValueAsString(loginResponseDto));
        driver.get(testProperties.getBaseUrl());
    }

    @AfterEach
    public void shouldDeleteUser() {

    }

}
