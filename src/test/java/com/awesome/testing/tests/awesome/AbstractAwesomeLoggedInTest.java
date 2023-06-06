package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.LoginApi;
import com.awesome.testing.api.dto.LoginResponseDto;
import com.awesome.testing.tests.SeleniumTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;

public abstract class AbstractAwesomeLoggedInTest extends SeleniumTest {

    private static final String ADMIN = "admin";
    private final LoginApi loginApi = new LoginApi();

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        driver.get(testProperties.getBaseUrl());
        LoginResponseDto loginResponseDto = loginApi.login(ADMIN, ADMIN);
        driver.manage().addCookie(new Cookie("token", loginResponseDto.getToken()));
        driver.getLocalStorage().setItem("user", new ObjectMapper().writeValueAsString(loginResponseDto));
        driver.get(testProperties.getBaseUrl());
    }

}
