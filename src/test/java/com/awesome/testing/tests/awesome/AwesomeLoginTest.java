package com.awesome.testing.tests.awesome;

import org.junit.jupiter.api.Test;

public class AwesomeLoginTest extends AwesomeTest {

    @Test
    public void shouldLogin() {
        awesomeLoginPage.attemptLogin("admin", "admin")
                .verifyUsersDisplayed();
    }

}
