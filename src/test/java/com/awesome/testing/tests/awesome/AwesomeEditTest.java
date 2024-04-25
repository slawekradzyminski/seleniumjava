package com.awesome.testing.tests.awesome;

import org.junit.jupiter.api.Test;

public class AwesomeEditTest extends AbstractAwesomeLoggedInTest {

    @Test
    public void shouldAutoFillCorrectData() {
        awesomeHomePage
                .assertThatAtLeastOneUserIsDisplayed()
                .clickEditOn(user)
                .verifyUserDataCorrectlyDisplayed(user);
    }

}
