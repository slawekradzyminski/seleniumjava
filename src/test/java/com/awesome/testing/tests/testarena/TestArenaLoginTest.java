package com.awesome.testing.tests.testarena;

import org.junit.jupiter.api.Test;

public class TestArenaLoginTest extends AbstractTestArenaTest {

    @Test
    public void shouldSuccessfullyLogin() {
        loginPage.attemptLogin(testProperties.getLogin(), testProperties.getPassword())
                .openProjects()
                .waitForTextAreaToLoad();
    }

}
