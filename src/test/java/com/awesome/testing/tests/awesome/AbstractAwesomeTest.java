package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.tests.AbstractSeleniumTest;
import org.junit.jupiter.api.BeforeEach;

public class AbstractAwesomeTest extends AbstractSeleniumTest {

    protected AwesomeLoginPage awesomeLoginPage;

    @BeforeEach
    public void navigate() {
        driver.get(properties.getUrl());
        awesomeLoginPage = new AwesomeLoginPage(driver);
    }

}
