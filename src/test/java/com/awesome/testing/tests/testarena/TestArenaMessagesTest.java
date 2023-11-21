package com.awesome.testing.tests.testarena;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestArenaMessagesTest extends AbstractTestArenaTest {

    private static final Faker FAKER = new Faker();

    @Test
    public void shouldSuccessfullyLogin() {
        String message = FAKER.lorem().characters(20);
        log.info(message);

        loginPage.attemptLogin(testProperties.getLogin(), testProperties.getPassword())
                .openMessagesPage()
                .waitForTextAreaToLoad()
                .addMessage(message)
                .verifyMessagePresent(message);
    }

}
