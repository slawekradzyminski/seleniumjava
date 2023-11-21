package com.awesome.testing.tests.testarena;

import com.awesome.testing.pages.testarena.TestArenaLoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractTestArenaTest extends SeleniumTest {

    protected TestArenaLoginPage loginPage;

    @BeforeEach
    public void navigate() {
        driver.get("http://demo.testarena.pl/zaloguj");
        loginPage = new TestArenaLoginPage(driver);
    }

}
