package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.ApiLogin;
import com.awesome.testing.api.dto.login.LoginDto;
import com.awesome.testing.api.dto.login.LoginResponseDto;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import com.awesome.testing.tests.SeleniumTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

public abstract class AbstractAwesomeLoggedInTest extends SeleniumTest {

    private final ApiLogin apiLogin = new ApiLogin();

    protected AwesomeHomePage awesomeHomePage;
    protected String token;

    @SneakyThrows
    @BeforeEach
    public void navigate() {
        driver.get("http://localhost:8081");
        LoginResponseDto loginResponse = apiLogin.login(new LoginDto("admin", "admin"));
        setLocalStorage(driver, loginResponse);
        setCookie(driver, loginResponse.getToken());
        driver.get("http://localhost:8081");
        token = loginResponse.getToken();
        awesomeHomePage = new AwesomeHomePage(driver);
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

}
