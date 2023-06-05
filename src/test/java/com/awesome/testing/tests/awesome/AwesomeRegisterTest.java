package com.awesome.testing.tests.awesome;

import com.awesome.testing.generator.dto.User;
import com.awesome.testing.pages.awesome.AwesomeRegisterPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generator.UserProvider.getRandomUser;
import static com.awesome.testing.generator.UserProvider.getRandomUserWithUsername;

public class AwesomeRegisterTest extends SeleniumTest {

    private AwesomeRegisterPage awesomeRegisterPage;

    @BeforeEach
    public void setUp() {
        driver.get(testProperties.getBaseUrl() + "/register");
        awesomeRegisterPage = new AwesomeRegisterPage(driver);
    }

    @Test
    public void shouldSuccessfullyRegister() {
        User user = getRandomUser();
        awesomeRegisterPage.attemptRegister(user)
                .verifyRegistrationSuccessMessage("Registration successful");
    }

    @Test
    public void shouldShowUserAlreadyExistsErrorMessage() {
        User user = getRandomUserWithUsername("admin");
        awesomeRegisterPage.attemptRegister(user, AwesomeRegisterPage.class)
                .verifyErrorMessageContains("Username is already in use");
    }

}
