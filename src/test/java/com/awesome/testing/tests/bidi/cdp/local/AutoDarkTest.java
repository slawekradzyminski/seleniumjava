package com.awesome.testing.tests.bidi.cdp.local;

import com.awesome.testing.tests.bidi.BidiTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.emulation.Emulation;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AutoDarkTest extends BidiTest {

    @BeforeEach
    public void setUp() {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setAutoDarkModeOverride(Optional.of(true)));
    }

    @Test
    public void shouldTurnOffTheLights() {
        // when
        driver.get("https://github.com/slawekradzyminski");

        // then
        assertThat(driver).isNotNull();
        log.info("There is no assertions, use debugger to see the dark theme :)");
    }

}
