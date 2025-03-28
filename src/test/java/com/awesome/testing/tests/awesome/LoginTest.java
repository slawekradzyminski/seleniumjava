package com.awesome.testing.tests.awesome;

import com.awesome.testing.http.RegisterApi;
import com.awesome.testing.http.dto.RegisterRequestDto;
import com.awesome.testing.pages.awesome.LoggedInHomePage;
import com.awesome.testing.pages.awesome.LoginPage;
import com.awesome.testing.tests.SeleniumTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generators.UserGenerator.getRandomUser;

@Slf4j
public class LoginTest extends SeleniumTest {

    @Test
    public void shouldSuccessfullyLogin() {
        // given
        RegisterRequestDto user = getRandomUser();
        RegisterApi.register(user);

        // when + then
        new LoginPage(driver)
                .openPage()
                .attemptLogin(user.getUsername(), user.getPassword(), LoggedInHomePage.class)
                .verifyIsLoaded(user.getFirstName(), user.getEmail());
    }

    @Test
    public void shouldShowAlertOfInvalidCredentials() {
        log.info("Using wrong credentials, expecting failed login");

        new LoginPage(driver)
                .openPage()
                .attemptLogin("wrong", "wrong", LoginPage.class)
                .getToast()
                .verifyErrorMessage("Invalid username/passwor");
    }

}
