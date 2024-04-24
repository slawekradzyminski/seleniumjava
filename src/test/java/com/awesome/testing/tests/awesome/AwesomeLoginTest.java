package com.awesome.testing.tests.awesome;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AwesomeLoginTest extends AbstractAwesomeTest {

    @ParameterizedTest
    @MethodSource("testData")
    public void shouldSuccessfullyLogin(String username, String password, String firstName) {
        awesomeLoginPage.attemptLogin(username, password)
                .assertThatHeaderContains(firstName);
    }

    public static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("admin", "admin", "Slawomir"),
                Arguments.of("client", "client", "Gosia")
        );
    }

    @Test
    public void shouldOpenRegisterPage() {
        awesomeLoginPage.clickRegister()
                .assertHeader();
    }

}
