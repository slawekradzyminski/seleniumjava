package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.LoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.Test;

public class LoginTest extends SeleniumTest {

    @Test
    public void shouldSuccessfullyLogin() {
        new LoginPage(driver)
                .openPage()
                .attemptLogin("admin", "admin")
                .verifyIsLoaded("Slawomir", "awesome@testing.com");
    }

}
