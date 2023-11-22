package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AwesomeHomeTest extends AbstractAwesomeLoggedInTest {

    private AwesomeHomePage homePage;

    @BeforeEach
    public void init() {
        homePage = new AwesomeHomePage(driver);
    }

    @Test
    public void shouldDisplayWelcomeHeader() {
        homePage
                .verifyHeaderForName(user.getFirstName());
    }

}
