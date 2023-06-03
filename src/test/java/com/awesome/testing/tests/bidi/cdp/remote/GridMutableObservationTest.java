package com.awesome.testing.tests.bidi.cdp.remote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.events.DomMutationEvent;
import org.openqa.selenium.logging.EventType;
import org.openqa.selenium.logging.HasLogEvents;
import org.openqa.selenium.remote.Augmenter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.devtools.events.CdpEventTypes.domMutation;

public class GridMutableObservationTest extends RemoteTest {

    private final AtomicReference<DomMutationEvent> seen = new AtomicReference<>();
    private final AtomicReference<WebDriver> augmentedDriver = new AtomicReference<>();
    private final CountDownLatch latch = new CountDownLatch(1);

    @BeforeEach
    public void setupEventListener() {
        driver = new Augmenter()
                .addDriverAugmentation("chrome",
                        HasLogEvents.class,
                        (caps, exec) -> new HasLogEvents() {
                            @Override
                            public <X> void onLogEvent(EventType<X> kind) {
                                kind.initializeListener(augmentedDriver.get());
                            }
                        }).augment(driver);

        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        augmentedDriver.set(driver);
        ((HasLogEvents) driver).onLogEvent(domMutation(mutation -> {
            seen.set(mutation);
            latch.countDown();
        }));
    }

    @Test
    public void mutableObservation() throws InterruptedException {
        // given
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // when
        driver.findElements(By.cssSelector("#checkboxes input")).get(0).click();

        // then
        assertThat(latch.await(10, SECONDS)).isTrue();
        assertThat(seen.get().getAttributeName()).isEqualTo("checked");
    }

}
