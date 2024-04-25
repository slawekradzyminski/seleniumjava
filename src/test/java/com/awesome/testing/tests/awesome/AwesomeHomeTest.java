package com.awesome.testing.tests.awesome;

import com.awesome.testing.api.RegisterApi;
import com.awesome.testing.dto.UserDto;
import com.awesome.testing.pages.awesome.AwesomeHomePage;
import com.awesome.testing.tests.AbstractSeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.awesome.testing.generators.UserGenerator.getRandomUser;

public class AwesomeHomeTest extends AbstractSeleniumTest {

    private AwesomeHomePage awesomeHomePage;
    private final RegisterApi registerApi = new RegisterApi();

    @BeforeEach
    public void setUp() {
        UserDto user = getRandomUser();
        registerApi.postSignUp(user);
        driver.get(properties.getUrl());
        // 1. wysłac POSTa na /users/signin

        // 2. odpowiedź ustawić w localStorage pod kluczem user

        // 3. odświeżając stronę powinniśmy być już zalogowani

        driver.get(properties.getUrl());

    }

    @Test
    public void shouldDisplayAtLeastOneUser() {
        awesomeHomePage.assertThatAtLeastOneUserIsDisplayed();
    }


}
