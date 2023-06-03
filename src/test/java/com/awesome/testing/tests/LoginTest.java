package com.awesome.testing.tests;

import org.junit.jupiter.api.Test;

public class LoginTest extends TestArenaTest {

    @Test
    public void shouldSuccessfullyLogin() {
        loginPage.attemptLogin(testProperties.getLogin(), testProperties.getPassword())
                .openProjects()
                .waitForTextAreaToLoad();
    }

}
