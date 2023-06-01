package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.ApiLogin;
import com.awesome.testing.api.ApiRegister;
import com.awesome.testing.api.dto.login.LoginResponseDto;
import com.awesome.testing.generator.User;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import com.awesome.testing.tests.SeleniumTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

import static com.awesome.testing.generator.UserProvider.getRandomUser;

public abstract class AbstractAwesomeLoggedInTest extends SeleniumTest {

    private final ApiLogin apiLogin = new ApiLogin();
    private final ApiRegister apiRegister = new ApiRegister();

    protected AwesomeHomePage awesomeHomePage;
    protected String token;

    @SneakyThrows
    @BeforeEach
    public void navigate() {
        driver.get("http://localhost:8081");
        User user = getRandomUser();
        apiRegister.register(user);
        LoginResponseDto loginResponse = apiLogin.login(user);
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
