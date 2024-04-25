package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.DeleteApi;
import com.awesome.testing.api.LoginApi;
import com.awesome.testing.api.RegisterApi;
import com.awesome.testing.dto.LoginResponseDto;
import com.awesome.testing.dto.UserDto;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import com.awesome.testing.tests.AbstractSeleniumTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;

import static com.awesome.testing.config.ObjectMapperProvider.OBJECT_MAPPER;
import static com.awesome.testing.generators.UserGenerator.getRandomUser;

public abstract class AbstractAwesomeLoggedInTest extends AbstractSeleniumTest {

    protected AwesomeHomePage awesomeHomePage;
    protected UserDto user;
    protected String token;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        user = getRandomUser();
        RegisterApi.postSignUp(user);
        driver.get(properties.getUrl());
        LoginResponseDto loginResponseDto = LoginApi.signIn(user.getUsername(), user.getPassword());
        token = loginResponseDto.getToken();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.setItem('%s','%s')",
                "user", OBJECT_MAPPER.writeValueAsString(loginResponseDto)));
        driver.get(properties.getUrl());
        awesomeHomePage = new AwesomeHomePage(driver);
    }

    @AfterEach
    public void cleanUpUser() {
        DeleteApi.deleteUser(user.getUsername(), token);
    }

}
