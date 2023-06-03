package com.awesome.testing.tests.awesome;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AwesomeLoginTest extends AbstractAwesomeTest {

    // https://www.baeldung.com/parameterized-tests-junit-5
    @ParameterizedTest
    @MethodSource("credentials")
    public void shouldLogin(String username, String password) {
        awesomeLoginPage.attemptLogin(username, password)
                .verifyUsersDisplayed();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void shouldLoginWithCsvSource(String username, String password) {
        awesomeLoginPage.attemptLogin(username, password)
                .verifyUsersDisplayed();
    }

    private static Stream<Arguments> credentials() {
        return Stream.of(
                Arguments.of("admin", "admin"),
                Arguments.of("client", "client")
        );
    }

}
