package com.awesome.testing.tests.awesome;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.http.LoginApi;
import com.awesome.testing.http.RegisterApi;
import com.awesome.testing.http.dto.RegisterRequestDto;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;

import static com.awesome.testing.generators.UserGenerator.getRandomUser;

public abstract class LoggedInSeleniumTest extends SeleniumTest {

    protected String token;
    protected RegisterRequestDto user;

    @BeforeEach
    public void loginUserAndSetLocalStorage() {
        user = getRandomUser();
        RegisterApi.register(user);
        token = LoginApi.login(user.getUsername(), user.getPassword());

        driver.get(ConfigProvider.get("frontend.url"));
        driver.executeScript("window.localStorage.setItem('token', arguments[0]);", token);
    }

}
