package com.awesome.testing.tests.awesome;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.pages.awesome.LoggedInHomePage;
import com.awesome.testing.pages.awesome.QrPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoggedInHeaderTest extends LoggedInSeleniumTest {

    @BeforeEach
    public void setUp() {
        driver.navigate().to(ConfigProvider.get("frontend.url"));
    }

    @Test
    public void shouldLogOutSuccessfully() {
        new LoggedInHomePage(driver)
                .getLoggedInHeader()
                .clickLogout()
                .verifyLoginUrl();
    }

    @Test
    public void shouldOpenProfilePage() {
        new LoggedInHomePage(driver)
                .getLoggedInHeader()
                .clickOnName(user.getFirstName(), user.getLastName())
                .verifyPersonalInformation(user);
    }

    @Test
    public void shouldOpenQrPage() {
        new LoggedInHomePage(driver)
                .getLoggedInHeader()
                .clickOnLink("QR Code", QrPage.class)
                .verifyIsLoaded();
    }
}
