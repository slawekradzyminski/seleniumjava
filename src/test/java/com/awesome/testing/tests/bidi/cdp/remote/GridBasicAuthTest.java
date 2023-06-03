package com.awesome.testing.tests.bidi.cdp.remote;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.remote.Augmenter;

import java.util.concurrent.atomic.AtomicReference;

public class GridBasicAuthTest extends RemoteTest {

    /**
     * Copy paste from https://github.com/SeleniumHQ/seleniumhq.github.io/blob/trunk//examples/java/src/test/java/dev/selenium/bidirectional/BidiApiRemotewebdriverTest.java
     * Not working...
     */
    @Disabled
    @Test
    public void basicAuth() {
        AtomicReference<DevTools> devToolsAtomicReference = new AtomicReference<>();

        driver = new Augmenter()
                .addDriverAugmentation("chrome",
                        HasAuthentication.class,
                        (caps, exec) -> (whenThisMatches, useTheseCredentials) -> {
                            devToolsAtomicReference.get()
                                    .createSessionIfThereIsNotOne();
                            devToolsAtomicReference.get().getDomains()
                                    .network()
                                    .addAuthHandler(whenThisMatches,
                                            useTheseCredentials);
                        }).augment(driver);

        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devToolsAtomicReference.set(devTools);
        ((HasAuthentication) driver).
                register(UsernameAndPassword.of("admin", "admin"));

        driver.get("https://the-internet.herokuapp.com/basic_auth");
        WebElement element = driver.findElement(By.tagName("p"));

        Assertions
                .assertEquals("Congratulations! You must have the proper credentials.", element.getText());
    }

}
