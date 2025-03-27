package com.awesome.testing.tests.awesome;

import com.awesome.testing.http.dto.RegisterRequestDto;
import com.awesome.testing.pages.awesome.LoginPage;
import com.awesome.testing.pages.awesome.RegisterPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generators.UserGenerator.getRandomUser;

public class RegisterTest extends SeleniumTest {

    @Test
    public void shouldSuccessfullyRegister() {
        RegisterRequestDto user = getRandomUser();

        new RegisterPage(driver)
                .openPage()
                .attemptRegister(user, LoginPage.class)
                .getToast()
                .verifySuccessMessage("Registration successful! You can now log in.");
    }

}
