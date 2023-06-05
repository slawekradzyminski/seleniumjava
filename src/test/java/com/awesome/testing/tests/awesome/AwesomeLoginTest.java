package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AwesomeLoginTest extends SeleniumTest {

    private AwesomeLoginPage awesomeLoginPage;

    @BeforeEach
    public void setUp() {
        driver.get("http://localhost:8081");
        awesomeLoginPage = new AwesomeLoginPage(driver);
    }

    @Test
    public void shouldSuccessfullyLogin() {
        awesomeLoginPage.attemptLogin("admin", "admin")
                .verifyWelcomeMessage("Hi Slawomir!");
    }

}
