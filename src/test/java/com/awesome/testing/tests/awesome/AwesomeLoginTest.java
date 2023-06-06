package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AwesomeLoginTest extends SeleniumTest {

    private AwesomeLoginPage awesomeLoginPage;

    @BeforeEach
    public void setUp() {
        driver.get(testProperties.getBaseUrl());
        awesomeLoginPage = new AwesomeLoginPage(driver);
    }

    @ParameterizedTest
    @MethodSource("credentials")
    public void shouldSuccessfullyLogin(String username, String password, String firstName) {
        awesomeLoginPage.attemptLogin(username, password)
                .verifyWelcomeMessage(String.format("Hi %s!", firstName));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/credentials.csv", numLinesToSkip = 1)
    public void shouldSuccessfullyLoginWithCsvTestData(String username, String password, String firstName) {
        awesomeLoginPage.attemptLogin(username, password)
                .verifyWelcomeMessage(String.format("Hi %s!", firstName));
    }

    @Test
    public void shouldShowErrorMessageOnFailedLogin() {
        awesomeLoginPage.attemptLogin("wrong", "wrong", AwesomeLoginPage.class)
                .getAwesomeAlert()
                .verifyErrorMessage("Invalid username/password supplied");
    }

    @Test
    public void shouldOpenRegisterPage() {
        awesomeLoginPage.clickRegister().verifyPageLoaded();

        assert 2 == 3;
    }

    private static Stream<Arguments> credentials() {
        return Stream.of(
                Arguments.of("client", "client", "Gosia"),
                Arguments.of("admin", "admin", "Slawomir")
        );
    }

}
