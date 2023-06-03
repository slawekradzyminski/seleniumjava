package com.awesome.testing.tests.arena;

import org.junit.jupiter.api.Test;

public class ArenaLoginTest extends ArenaTest {

    @Test
    public void shouldSuccessfullyLogin() {
        arenaLoginPage.attemptLogin(testProperties.getLogin(), testProperties.getPassword())
                .openProjects()
                .waitForTextAreaToLoad();
    }

}
