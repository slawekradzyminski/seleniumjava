package com.awesome.testing.tests.bidi.w3c.remote;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.bidi.LogInspector;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.bidi.log.JavascriptLogEntry;
import org.openqa.selenium.bidi.log.LogLevel;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

/**
 * Doesn't work... why?
 */
@Disabled
public class GridLogInspectorTest extends RemoteFirefoxTest {

    private static final String SELENIUM_CUSTOM_PAGE = "https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html";
    private List<ConsoleLogEntry> consoleLogEntries;
    private List<JavascriptLogEntry> javascriptLogEntries;
    private List<JavascriptLogEntry> javascriptExceptions;

    @SuppressWarnings("resource")
    @BeforeEach
    public void setUp() {
        consoleLogEntries = new ArrayList<>();
        javascriptLogEntries = new ArrayList<>();
        javascriptExceptions = new ArrayList<>();
        LogInspector logInspector = new LogInspector(driver);
        logInspector.onJavaScriptLog(log -> javascriptLogEntries.add(log));
        logInspector.onConsoleEntry(log -> consoleLogEntries.add(log));
        logInspector.onJavaScriptException(log -> javascriptExceptions.add(log));
    }

    @SneakyThrows
    @Test
    void testListenToConsoleLog() {
        // given
        driver.get(SELENIUM_CUSTOM_PAGE);

        // when
        driver.findElement(By.id("consoleLog")).click();

        // then
        await().until(() -> consoleLogEntries.size() == 1);
        ConsoleLogEntry consoleLogEntry = consoleLogEntries.get(0);
        assertThat(consoleLogEntry.getText()).isEqualTo("Hello, world!");
        assertThat(consoleLogEntry.getRealm()).isNull();
        assertThat(consoleLogEntry.getType()).isEqualTo("console");
        assertThat(consoleLogEntry.getMethod()).isEqualTo("log");
        assertThat(consoleLogEntry.getStackTrace()).isNull();
    }

    @SneakyThrows
    @Test
    void testListenToJavascriptLog() {
        // given
        driver.get(SELENIUM_CUSTOM_PAGE);

        // when
        driver.findElement(By.id("jsException")).click();

        // then
        await().until(() -> javascriptLogEntries.size() == 1);
        JavascriptLogEntry javascriptLogEntry = javascriptLogEntries.get(0);
        assertThat(javascriptLogEntry.getText()).isEqualTo("Error: Not working");
        assertThat(javascriptLogEntry.getType()).isEqualTo("javascript");
        assertThat(javascriptLogEntry.getLevel()).isEqualTo(LogLevel.ERROR);
    }

    @SneakyThrows
    @Test
    void testListenToJavascriptErrorLog() {
        // given
        driver.get(SELENIUM_CUSTOM_PAGE);

        // when
        driver.findElement(By.id("jsException")).click();

        // then
        await().until(() -> javascriptExceptions.size() == 1);
        JavascriptLogEntry javascriptLogEntry = javascriptExceptions.get(0);
        assertThat(javascriptLogEntry.getText()).isEqualTo("Error: Not working");
        assertThat(javascriptLogEntry.getType()).isEqualTo("javascript");
    }

    @SneakyThrows
    @Test
    void testRetrieveStacktraceForALog() {
        // given
        driver.get(SELENIUM_CUSTOM_PAGE);

        // when
        driver.findElement(By.id("logWithStacktrace")).click();

        // then
        await().until(() -> javascriptExceptions.size() == 1);
        JavascriptLogEntry javascriptLogEntry = javascriptExceptions.get(0);
        assertThat(javascriptLogEntry.getStackTrace()).isNotNull();
        assertThat(javascriptLogEntry.getStackTrace().getCallFrames()).hasSize(4);
    }

}
