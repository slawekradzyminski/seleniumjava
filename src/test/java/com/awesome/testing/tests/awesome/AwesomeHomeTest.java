package com.awesome.testing.tests.awesome;

import org.junit.jupiter.api.Test;
public class AwesomeHomeTest extends AbstractAwesomeLoggedInTest {

    @Test
    public void shouldDisplayUser() {
        awesomeHomePage.verifyUsersDisplayed();
    }

}