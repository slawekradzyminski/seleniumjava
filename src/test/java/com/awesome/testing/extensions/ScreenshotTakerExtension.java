package com.awesome.testing.extensions;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

@Slf4j
public class ScreenshotTakerExtension implements AfterTestExecutionCallback {

    @Setter
    private static WebDriver driver;

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        if (extensionContext.getExecutionException().isPresent()) {
            log.info("Detected test failure. Creating a screenshot...");
        }
    }

}
