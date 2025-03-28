package com.awesome.testing.listeners;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

@Slf4j
public class TestExecutionListener implements WebDriverListener {

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        String elementDescription = element.toString();
        String cleanedLocator = extractLocator(elementDescription);
        log.info("Inserted {} to element {}", keysToSend, cleanedLocator);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        log.info("Navigated to {}", url);
    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, String url) {
        log.info("Navigated to {}", url);
    }

    private String extractLocator(String elementDescription) {
        int arrowIndex = elementDescription.lastIndexOf("->");
        if (arrowIndex != -1) {
            return elementDescription.substring(arrowIndex + 3, elementDescription.length() - 1).trim();
        }
        return elementDescription;
    }
}
