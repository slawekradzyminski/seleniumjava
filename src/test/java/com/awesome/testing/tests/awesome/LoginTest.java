package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.LoggedInHomePage;
import com.awesome.testing.pages.awesome.LoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.Test;

public class LoginTest extends SeleniumTest {

    @Test
    public void shouldSuccessfullyLogin() {
        new LoginPage(driver)
                .openPage()
                .attemptLogin("admin", "admin", LoggedInHomePage.class)
                .verifyIsLoaded("Slawomir", "awesome@testing.com");
    }

    @Test
    public void shouldShowAlertOfInvalidCredentials() {
        new LoginPage(driver)
                .openPage()
                .attemptLogin("wrong", "wrong", LoginPage.class)
                .getToast()
                .verifyErrorMessage("Invalid username/password");
    }

}
