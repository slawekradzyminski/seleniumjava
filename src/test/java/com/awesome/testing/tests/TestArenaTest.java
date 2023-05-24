package com.awesome.testing.tests;

import com.awesome.testing.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;

public class TestArenaTest extends LocalTest {

    protected LoginPage loginPage;

    @BeforeEach
    public void navigate() {
        driver.get("http://demo.testarena.pl/zaloguj");
        loginPage = new LoginPage(driver);
    }

}
