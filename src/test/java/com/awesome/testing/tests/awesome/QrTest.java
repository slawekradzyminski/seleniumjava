package com.awesome.testing.tests.awesome;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.pages.awesome.QrPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QrTest extends LoggedInSeleniumTest {

    @BeforeEach
    public void setUp() {
        driver.navigate().to(ConfigProvider.get("frontend.url") + "/qr");
    }

    @Test
    public void shouldGenerateQrCodeAndClearItSuccessfully() {
        new QrPage(driver)
                .generateQr("https://www.awesome-testing.com")
                .assertQrCodeDisplayed()
                .clearQrCode()
                .assertQrCodeNotDisplayed();
    }

}
