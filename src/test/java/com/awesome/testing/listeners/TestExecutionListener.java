package com.awesome.testing.listeners;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

@Slf4j
public class TestExecutionListener implements WebDriverListener {

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("About to sendKeys {} to element located in {}", keysToSend, element.getLocation());
    }

    @Override
    public void beforeGet(WebDriver driver, String url) {
        log.info("Entering {} page", url);
    }
}
