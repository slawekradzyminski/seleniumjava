package com.awesome.testing.cdp.local;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.devtools.DevTools;

import java.util.ArrayList;
import java.util.List;

import static org.awaitility.Awaitility.await;

public class JavascriptExceptionLoggerTest extends LocalTest {

    private final List<JavascriptException> jsExceptionsList = new ArrayList<>();

    @BeforeEach
    public void setUpLogger() {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.getDomains().events().addJavascriptExceptionListener(jsExceptionsList::add);
    }

    @Test
    public void logEvents() {
        // when
        driver.get("http://the-internet.herokuapp.com/javascript_error");

        // then
        await().until(() -> jsExceptionsList.size() > 0);
    }
}
