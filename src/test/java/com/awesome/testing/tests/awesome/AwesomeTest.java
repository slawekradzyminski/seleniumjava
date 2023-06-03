package com.awesome.testing.tests.awesome;

import com.awesome.testing.pages.awesome.AwesomeLoginPage;
import com.awesome.testing.tests.SeleniumTest;
import org.junit.jupiter.api.BeforeEach;

public class AwesomeTest extends SeleniumTest {

    protected AwesomeLoginPage awesomeLoginPage;

    @BeforeEach
    public void navigate() {
        driver.get("http://localhost:8081");
        awesomeLoginPage = new AwesomeLoginPage(driver);
    }

}
