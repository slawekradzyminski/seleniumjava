package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeHomePage;
import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AwesomeLoginTest extends AbstractAwesomeTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata.csv", numLinesToSkip = 1)
    public void shouldSuccessfullyLogin(String username, String password, String firstName) {
        awesomeLoginPage.attemptLogin(username, password, AwesomeHomePage.class)
                .assertThatHeaderContains(firstName);
    }

    @Test
    public void shouldFailToLogin() {
        awesomeLoginPage.attemptLogin("wrong", "wrong", AwesomeLoginPage.class)
                .verifyAlertFailureMessage("Invalid username/password supplied");
    }

    @Test
    public void shouldOpenRegisterPage() {
        awesomeLoginPage.clickRegister()
                .assertHeader();
    }

}
