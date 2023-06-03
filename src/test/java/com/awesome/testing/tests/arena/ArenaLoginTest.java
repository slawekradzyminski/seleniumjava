package com.awesome.testing.tests.arena;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArenaLoginTest extends AbstractArenaTest {

    @Test
    public void shouldSuccessfullyLogin() {
        arenaLoginPage.attemptLogin(testProperties.getLogin(), testProperties.getPassword())
                .openProjects()
                .waitForTextAreaToLoad();

        assertThat(1).isEqualTo(3);
    }

}
