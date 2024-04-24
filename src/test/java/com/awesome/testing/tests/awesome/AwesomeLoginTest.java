package com.awesome.testing.tests.awesome;

import org.junit.jupiter.api.Test;

public class AwesomeLoginTest extends AbstractAwesomeTest {

    @Test
    public void shouldSuccessfullyLogin() {
        awesomeLoginPage.attemptLogin("admin", "admin")
                .assertThatHeaderContains("Slawomir");
    }

    @Test
    public void shouldOpenRegisterPage() {
        awesomeLoginPage.clickRegister()
                .assertHeader();
    }

}
