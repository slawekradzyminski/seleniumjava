package com.awesome.testing.tests.testarena;

import org.junit.jupiter.api.Test;

import static com.awesome.testing.generators.MessageGenerator.getRandomMessage;

public class TAAddMessageTest extends AbstractTestArenaTest {

    @Test
    public void shouldAddMessage() {
        String message = getRandomMessage();

        TALoginPage.attemptLogin(properties.getLogin(), properties.getPassword())
                .openMessages()
                .waitForTextAreaToLoad()
                .addMessage(message)
                .verifyLastMessageIs(message);
    }

}
