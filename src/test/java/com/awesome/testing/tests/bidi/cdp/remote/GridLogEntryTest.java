package com.awesome.testing.tests.bidi.cdp.remote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v133.log.Log;
import org.openqa.selenium.devtools.v133.log.model.LogEntry;
import org.openqa.selenium.remote.Augmenter;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GridLogEntryTest extends RemoteTest {

    private final List<LogEntry> events = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        driver = new Augmenter().augment(driver);
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Log.enable());
        devTools.addListener(Log.entryAdded(), this::addErrorEvent);
    }

    @Test
    public void logEvents() {
        // when
        driver.get("http://the-internet.herokuapp.com/broken_images");

        // then
        assertThat(events.size()).isPositive();
    }

    private void addErrorEvent(LogEntry logEntry) {
        if (logEntry.getLevel() == LogEntry.Level.ERROR) {
            events.add(logEntry);
        }
    }

}
