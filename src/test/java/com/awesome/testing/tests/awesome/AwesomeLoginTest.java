package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.RegisterApi;
import com.awesome.testing.dto.UserDto;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.tests.AbstractSeleniumTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.awesome.testing.generators.UserGenerator.getRandomUser;

public class AwesomeLoginTest extends AbstractSeleniumTest {

    private AwesomeLoginPage awesomeLoginPage;

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
        // given
        UserDto user = getRandomUser();
        RegisterApi.postSignUp(user);

        // when
        var awesomeHomePage = awesomeLoginPage.attemptLogin(user.getUsername(), user.getPassword(), AwesomeHomePage.class);

        // then
        awesomeHomePage.assertThatHeaderContains(user.getFirstName());
    }

    @Test
    public void shouldFailToLogin() {
        awesomeLoginPage.attemptLogin("wrong", "wrong", AwesomeLoginPage.class)
                .getAlertComponent()
                .verifyAlertFailureMessage("Invalid username/password supplied");
    }

    @SneakyThrows
    @Test
    public void shouldOpenRegisterPage() {
        awesomeLoginPage.clickRegister()
                .assertHeader();

        assert 2 == 3;
    }

}
