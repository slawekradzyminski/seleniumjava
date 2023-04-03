package com.awesome.testing.cdp.remote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.remote.Augmenter;

import java.util.ArrayList;
import java.util.List;

import static org.awaitility.Awaitility.await;

public class GridJavascriptExceptionLoggerTest extends RemoteTest {

    private final List<JavascriptException> jsExceptionsList = new ArrayList<>();

    @BeforeEach
    public void setUpLogger() {
        driver = new Augmenter().augment(driver);
        DevTools devTools = ((HasDevTools) driver).getDevTools();
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
