package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractAwesomeTest extends SeleniumTest {

    protected AwesomeLoginPage loginPage;

    @BeforeEach
    public void openPage() {
        driver.get(testProperties.getUrl());
        loginPage = new AwesomeLoginPage(driver);
    }

}
