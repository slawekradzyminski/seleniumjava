package com.awesome.testing.tests.bidi.cdp.local;

import com.awesome.testing.tests.bidi.BidiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v158.browser.Browser;
import org.openqa.selenium.devtools.v158.browser.model.PermissionType;
import org.openqa.selenium.devtools.v158.emulation.Emulation;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class GeoLocationTest extends BidiTest {

    @BeforeEach
    public void setUp() {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Browser.grantPermissions(
                List.of(PermissionType.GEOLOCATION),
                Optional.of("https://my-location.org"),
                Optional.empty()));
        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(52.5043),
                Optional.of(13.4501),
                Optional.of(1),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));
    }

    @Test
    public void shouldShowBerlinGeoLocation() {
        // when
        driver.get("https://my-location.org/");

        // then
        await().atMost(Duration.ofSeconds(10)).untilAsserted(() ->
                assertThat(driver.findElement(By.id("address")).getText()).contains("Berlin"));
    }

}
