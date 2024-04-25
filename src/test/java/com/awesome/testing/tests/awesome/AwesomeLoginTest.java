package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.RegisterApi;
import com.awesome.testing.dto.UserDto;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.tests.AbstractSeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.awesome.testing.generators.UserGenerator.getRandomUser;

public class AwesomeLoginTest extends AbstractSeleniumTest {

    private AwesomeLoginPage awesomeLoginPage;
    private final RegisterApi registerApi = new RegisterApi();

    @BeforeEach
    public void navigate() {
        driver.get(properties.getUrl());
        awesomeLoginPage = new AwesomeLoginPage(driver);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdata.csv", numLinesToSkip = 1)
    public void shouldSuccessfullyLogin(String username, String password, String firstName) {
        awesomeLoginPage.attemptLogin(username, password, AwesomeHomePage.class)
                .assertThatHeaderContains(firstName);
    }

    @Test
    public void shouldSuccessfullyLoginToNewlyRegisteredUser() {
        UserDto user = getRandomUser();
        registerApi.postSignUp(user);

        awesomeLoginPage.attemptLogin(user.getUsername(), user.getPassword(), AwesomeHomePage.class)
                .assertThatHeaderContains(user.getFirstName());
    }

    @Test
    public void shouldFailToLogin() {
        awesomeLoginPage.attemptLogin("wrong", "wrong", AwesomeLoginPage.class)
                .getAlertComponent()
                .verifyAlertFailureMessage("Invalid username/password supplied");
    }

    @Test
    public void shouldOpenRegisterPage() {
        awesomeLoginPage.clickRegister()
                .assertHeader();
    }

}
