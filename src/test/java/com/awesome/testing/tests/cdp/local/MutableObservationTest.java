package com.awesome.testing.tests.cdp.local;

import com.awesome.testing.tests.LocalTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.events.DomMutationEvent;
import org.openqa.selenium.logging.HasLogEvents;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.devtools.events.CdpEventTypes.domMutation;

public class MutableObservationTest extends LocalTest {

    private final AtomicReference<DomMutationEvent> seen = new AtomicReference<>();
    private final CountDownLatch latch = new CountDownLatch(1);

    @BeforeEach
    public void setupEventListener() {
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
