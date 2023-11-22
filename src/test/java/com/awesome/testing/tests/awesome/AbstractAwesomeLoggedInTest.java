package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.DeleteUserApi;
import com.awesome.testing.api.LoginApi;
import com.awesome.testing.api.RegisterApi;
import com.awesome.testing.api.dto.LoginRequestDto;
import com.awesome.testing.api.dto.LoginResponseDto;
import com.awesome.testing.generators.UserDto;
import com.awesome.testing.tests.SeleniumTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.html5.WebStorage;

public abstract class AbstractAwesomeLoggedInTest extends SeleniumTest {

    private final RegisterApi registerApi = new RegisterApi();
    private final LoginApi loginApi = new LoginApi();
    private final DeleteUserApi deleteUserApi = new DeleteUserApi();

    protected String token;
    protected UserDto user;

    @SneakyThrows
    @BeforeEach
    public void logIn() {
        driver.get("http://localhost:8081");
        user = UserDto.getRandomUser();
        registerApi.register(user);
        LoginResponseDto loginResponseDto = loginApi.login(new LoginRequestDto(user.getUsername(), user.getPassword()));
        ((WebStorage) driver).getLocalStorage().setItem("user", new ObjectMapper().writeValueAsString(loginResponseDto));
        driver.manage().addCookie(new Cookie("token", loginResponseDto.getToken()));
        token = loginResponseDto.getToken();
        driver.navigate().to("http://localhost:8081");
    }

    @AfterEach
    public void cleanUpUser() {
        deleteUserApi.deleteUser(user.getUsername(), token);
    }

}
