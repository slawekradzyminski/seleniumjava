package com.awesome.testing.tests.testarena;

import com.awesome.testing.pages.testarena.TALoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;

public class AbstractTestArenaTest extends SeleniumTest {

    protected TALoginPage TALoginPage;

    @BeforeEach
    public void navigate() {
        driver.get("http://demo.testarena.pl/zaloguj");
        TALoginPage = new TALoginPage(driver);
    }

}
