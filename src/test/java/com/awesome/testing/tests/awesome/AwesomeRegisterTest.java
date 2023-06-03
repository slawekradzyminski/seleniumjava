package com.awesome.testing.tests.awesome;

import com.awesome.testing.generator.User;
import com.awesome.testing.pages.awesome.AwesomeRegisterPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generator.UserProvider.getRandomUser;

public class AwesomeRegisterTest extends SeleniumTest {

    private AwesomeRegisterPage awesomeRegisterPage;

    @BeforeEach
    public void setUp() {
        driver.get("http://localhost:8081/register");
        awesomeRegisterPage = new AwesomeRegisterPage(driver);
    }

    @Test
    public void shouldSuccessfullyRegister() {
        User user = getRandomUser();
        awesomeRegisterPage.attemptRegister(user)
                .verifyRegistrationSuccessfulMessage();
    }

}
