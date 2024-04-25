package com.awesome.testing.tests.awesome;

import com.awesome.testing.dto.UserDto;
import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.pages.awesome.AwesomeRegisterPage;
import com.awesome.testing.tests.AbstractSeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generators.UserGenerator.getRandomUser;

public class AwesomeRegisterTest extends AbstractSeleniumTest {

    private AwesomeRegisterPage awesomeRegisterPage;

    @BeforeEach
    public void navigate() {
        driver.get(properties.getUrl() + "/register");
        awesomeRegisterPage = new AwesomeRegisterPage(driver);
    }

    @Test
    public void shouldSuccessfullyRegisterUser() {
        UserDto userDto = getRandomUser();
        awesomeRegisterPage.attemptRegister(userDto, AwesomeLoginPage.class)
                .getAlertComponent()
                .verifyAlertSuccessMessage("Registration successful");
    }

    @Test
    public void shouldFailToRegister() {
        UserDto userDto = getRandomUser();
        userDto.setUsername("admin");

        awesomeRegisterPage.attemptRegister(userDto, AwesomeRegisterPage.class)
                .getAlertComponent()
                .verifyAlertFailureMessage("Username is already in use");
    }

}
