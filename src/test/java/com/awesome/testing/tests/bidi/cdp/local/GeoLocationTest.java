package com.awesome.testing.tests.bidi.cdp.local;

import com.awesome.testing.tests.bidi.BidiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.emulation.Emulation;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GeoLocationTest extends BidiTest {

    @BeforeEach
    public void setUp() {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(52.5043),
                Optional.of(13.4501),
                Optional.of(1)));
    }

    @Test
    public void shouldShowBerlinGeoLocation() {
        // when
        driver.get("https://my-location.org/");

        // then
        assertThat(driver.findElement(By.id("address")).getText()).contains("Berlin");
    }

}
