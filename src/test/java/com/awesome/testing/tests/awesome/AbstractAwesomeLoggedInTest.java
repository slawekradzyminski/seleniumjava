package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.LoginApi;
import com.awesome.testing.api.dto.LoginResponseDto;
import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractAwesomeLoggedInTest extends SeleniumTest {

    private static final String ADMIN = "admin";
    private final LoginApi loginApi = new LoginApi();

    @BeforeEach
    public void setUp() {
        driver.get(testProperties.getBaseUrl());
        LoginResponseDto login = loginApi.login(ADMIN, ADMIN);

        new AwesomeLoginPage(driver).attemptLogin(ADMIN, ADMIN)
                .verifyWelcomeMessage("Hi Slawomir!");
    }

}
