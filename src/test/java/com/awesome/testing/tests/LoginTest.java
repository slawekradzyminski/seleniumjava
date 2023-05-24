package com.awesome.testing.tests;

import org.junit.jupiter.api.Test;

public class LoginTest extends TestArenaTest {

    @Test
    public void shouldSuccessfullyLogin() {
        loginPage.attemptLogin("administrator@testarena.pl", "sumXQQ72$L")
                .openProjects()
                .waitForTestAreaToLoad();
    }

}
