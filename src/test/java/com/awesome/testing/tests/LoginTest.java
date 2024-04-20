package com.awesome.testing.tests;

import org.junit.jupiter.api.Test;

public class LoginTest extends AbstractTestArenaTest {

    @Test
    public void shouldSuccessfullyLogin() {
        TALoginPage.attemptLogin("administrator@testarena.pl", "sumXQQ72$L")
                .openProjects()
                .waitForTextAreaToLoad();
    }

}
