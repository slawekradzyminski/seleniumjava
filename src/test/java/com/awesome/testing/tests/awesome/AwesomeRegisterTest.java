package com.awesome.testing.tests.awesome;

import com.awesome.testing.generators.UserDto;
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
//        registerPage.attemptRegister(user);
    }
}
