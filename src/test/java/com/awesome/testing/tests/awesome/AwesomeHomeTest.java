package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AwesomeHomeTest extends AbstractAwesomeLoggedInTest {

    private AwesomeHomePage awesomeHomePage;

    @BeforeEach
    public void loadHome() {
        awesomeHomePage = new AwesomeHomePage(driver);
    }

    @Test
    public void shouldDisplayWelcomeMessage() {
        awesomeHomePage.verifyWelcomeMessage(String.format("Hi %s!", user.getFirstName()));
    }

    @Test
    public void shouldDisplayUsersInTheList() {
        awesomeHomePage.verifyUsersDisplayed();
    }

    @Test
    public void shouldLogOut() {
        awesomeHomePage.clickLogout().verifyHeader();
    }

}
