package com.awesome.testing.tests.awesome;

import com.awesome.testing.generators.UserDto;
import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.pages.awesome.AwesomeRegisterPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AwesomeRegisterTest extends SeleniumTest {

    protected AwesomeRegisterPage registerPage;

    @BeforeEach
    public void openPage() {
        driver.get(testProperties.getUrl() + "/register");
        registerPage = new AwesomeRegisterPage(driver);
    }

    @Test
    public void shouldSuccessfullyRegister() {
        UserDto user = UserDto.getRandomUser();
        registerPage.attemptRegister(user, AwesomeLoginPage.class)
                .getAlert()
                .verifyAlertSuccess("Registration successful");
    }

    @Test
    public void shouldShowErrorMessageOnUserAlreadyExists() {
        UserDto user = UserDto.getRandomUserBuilder()
                .username("admin")
                .build();

        registerPage.attemptRegister(user, AwesomeRegisterPage.class)
                .getAlert()
                .verifyAlertFailure("Username is already in use");
    }
}
