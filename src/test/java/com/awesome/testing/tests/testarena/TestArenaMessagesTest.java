package com.awesome.testing.tests.testarena;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestArenaMessagesTest extends AbstractTestArenaTest {

    @Test
    public void shouldSuccessfullyLogin() {
        String message = RandomStringUtils.randomAlphanumeric(20);

        loginPage.attemptLogin(testProperties.getLogin(), testProperties.getPassword())
                .openMessagesPage()
                .waitForTextAreaToLoad()
                .addMessage(message)
                .verifyMessagePresent(message);
    }

}
