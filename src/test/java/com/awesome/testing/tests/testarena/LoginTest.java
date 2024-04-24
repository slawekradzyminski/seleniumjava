package com.awesome.testing.tests.testarena;

import org.junit.jupiter.api.Test;

public class LoginTest extends AbstractTestArenaTest {

    @Test
    public void shouldSuccessfullyLogin() {
        TALoginPage.attemptLogin(properties.getLogin(), properties.getPassword())
                .openMessages()
                .waitForTextAreaToLoad();
    }

}
