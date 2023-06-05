package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeHomePage;
import org.junit.jupiter.api.Test;

public class AwesomeHomeTest extends AbstractAwesomeLoggedInTest {

    @Test
    public void shouldDisplayUsers() {
        new AwesomeHomePage(driver);
    }

}
