package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractAwesomeLoggedInTest extends SeleniumTest {

    @BeforeEach
    public void setUp() {
        driver.get(testProperties.getBaseUrl());
        new AwesomeLoginPage(driver).attemptLogin("admin", "admin")
                .verifyWelcomeMessage("Hi Slawomir!");
    }

}
