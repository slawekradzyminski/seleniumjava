package com.awesome.testing.tests.awesome;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class AwesomeLoginTest extends AbstractAwesomeTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void shouldSuccessfullyLogin(String username, String password, String firstName) {
        loginPage.attemptLogin(username, password)
                .verifyHeaderForName(firstName);
    }

}
