package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeHomePage;
import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AwesomeLoginTest extends SeleniumTest {

    protected AwesomeLoginPage loginPage;

    @BeforeEach
    public void openPage() {
        driver.get(testProperties.getUrl());
        loginPage = new AwesomeLoginPage(driver);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void shouldSuccessfullyLogin(String username, String password, String firstName) {
        loginPage.attemptLogin(username, password, AwesomeHomePage.class)
                .verifyHeaderForName(firstName);
    }

    @Test
    public void shouldFailLogin() {
        loginPage.attemptLogin("wrong", "wrong", AwesomeLoginPage.class)
                .verifyAlertError();
    }

    @Test
    public void shouldOpenRegisterPage() {
        loginPage.clickRegister()
                .verifyHeader();
    }
}
