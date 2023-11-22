package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeEditPage;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AwesomeEditTest extends AbstractAwesomeLoggedInTest {

    private AwesomeEditPage editPage;

    @BeforeEach
    public void init() {
        AwesomeHomePage homePage = new AwesomeHomePage(driver);
        editPage = homePage.clickEditOnSpecificUser(user);
    }

    @Test
    public void shouldCorrectlyAutofillData() {
        editPage.verifyUserDataAutocompleted(user);
    }

    @Test
    public void shouldCorrectlyEditUser() {

    }

}
