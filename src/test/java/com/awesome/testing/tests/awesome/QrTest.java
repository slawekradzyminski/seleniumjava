package com.awesome.testing.tests.awesome;

import com.awesome.testing.config.ConfigProvider;
import org.junit.jupiter.api.BeforeEach;

public class QrTest extends LoggedInSeleniumTest {

    @BeforeEach
    public void setUp() {
        driver.navigate().to(ConfigProvider.get("frontend.url") + "/qr");
    }

}
