package com.awesome.testing.tests;

import com.awesome.testing.http.LoginClient;
import com.awesome.testing.pages.awesome.HomePage;
import org.junit.jupiter.api.Test;

public class ApiLoginFrontendTest extends SeleniumTest {

    @Test
    public void shouldLoginViaApiAndAccessFrontend() {
        // given
        String jwtToken = LoginClient.loginAsAdmin();
        driver.get("http://localhost:8081");
        driver.executeScript("window.localStorage.setItem('token', arguments[0]);", jwtToken);

        // when
        driver.navigate().to("http://localhost:8081");

        // then
        new HomePage(driver).verifyEmail("awesome@testing.com");
    }
    
} 