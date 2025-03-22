package com.awesome.testing.tests;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.http.LoginClient;
import com.awesome.testing.pages.awesome.HomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

public class ApiLoginFrontendTest extends SeleniumTest {

    @Test
    public void shouldLoginViaApiAndAccessFrontend() {
        // given
        String jwtToken = LoginClient.loginAsAdmin();
        driver.get(ConfigProvider.get("frontend.url"));
        ((JavascriptExecutor) driver)
                .executeScript("window.localStorage.setItem('token', arguments[0]);", jwtToken);

        // when
        driver.navigate().to(ConfigProvider.get("frontend.url"));

        // then
        new HomePage(driver).verifyEmail("awesome@testing.com");
    }

} 