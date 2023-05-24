package com.awesome.testing.tests.cdp.remote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v111.emulation.Emulation;
import org.openqa.selenium.remote.Augmenter;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GridGeoLocationTest extends RemoteTest {

    @BeforeEach
    public void setUp() {
        driver = new Augmenter().augment(driver);
        DevTools devTools = ((HasDevTools) driver).getDevTools();
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
