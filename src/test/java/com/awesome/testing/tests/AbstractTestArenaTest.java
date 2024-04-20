package com.awesome.testing.tests;

import com.awesome.testing.pages.testarena.TALoginPage;
import org.junit.jupiter.api.BeforeEach;

public class AbstractTestArenaTest extends SeleniumTest {

    protected TALoginPage TALoginPage;

    @BeforeEach
    public void navigate() {
        driver.get("http://demo.testarena.pl/zaloguj");
        TALoginPage = new TALoginPage(driver);
    }

}
