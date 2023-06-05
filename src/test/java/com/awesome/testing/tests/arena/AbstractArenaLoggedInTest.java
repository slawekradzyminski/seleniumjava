package com.awesome.testing.tests.arena;

import com.awesome.testing.pages.arena.ArenaLoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;

public class AbstractArenaLoggedInTest extends SeleniumTest {

    @BeforeEach
    public void logIn() {
        driver.get("http://demo.testarena.pl/zaloguj");
        new ArenaLoginPage(driver).attemptLogin(testProperties.getLogin(), testProperties.getPassword())
                .verifyLoginSucceeded();
    }

}
