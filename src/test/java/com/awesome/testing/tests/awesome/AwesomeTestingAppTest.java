package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.ApiLogin;
import com.awesome.testing.api.dto.login.LoginResponseDto;
import com.awesome.testing.tests.LocalTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

public class AwesomeTestingAppTest extends LocalTest {

    ApiLogin apiLogin = new ApiLogin();

    @SneakyThrows
    @BeforeEach
    public void navigate() {
        driver.get("http://localhost:8081");
        LoginResponseDto loginResponse = apiLogin.login(null);
        setLocalStorage(driver, loginResponse);
        setCookie(driver, loginResponse.getToken());
        driver.get("http://localhost:8081");

    }

    private void setCookie(WebDriver driver, String token) {
        driver.manage().addCookie(new Cookie("token", token));
    }

    @SneakyThrows
    private void setLocalStorage(WebDriver driver, LoginResponseDto loginResponse) {
        String userLocalStorageEntry = new ObjectMapper().writeValueAsString(loginResponse);
        LocalStorage local = ((WebStorage) driver).getLocalStorage();
        local.setItem("user", userLocalStorageEntry);
    }

    @Test
    public void lalalal() {

    }

}
