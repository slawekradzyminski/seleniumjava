package com.awesome.testing.extensions;

import ch.qos.logback.classic.Logger;
import com.awesome.testing.logging.InMemoryAppender;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.*;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

@Slf4j
@Order(2)
public class AllureLoggingExtension implements BeforeEachCallback, AfterEachCallback {

    private InMemoryAppender inMemoryAppender;

    @Override
    public void beforeEach(ExtensionContext context) {
        // Obtain the ROOT logger (this is Logback-specific!)
        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

        // Create and start our appender
        inMemoryAppender = new InMemoryAppender();
        inMemoryAppender.start();

        // Attach it to the root logger
        rootLogger.addAppender(inMemoryAppender);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        // Grab all logs from the in-memory appender
        String allLogs = inMemoryAppender.getAllLogs();

        // Attach them to Allure
        // The extension name + test name can help you identify which test the logs came from
        String testName = context.getRequiredTestClass().getSimpleName() + "#" +
                context.getRequiredTestMethod().getName();
        Allure.addAttachment("Logs for " + testName, "text/plain", new ByteArrayInputStream(allLogs.getBytes()), ".txt");

        // Detach our appender so it does not continue collecting logs for subsequent tests
        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.detachAppender(inMemoryAppender);
    }
}

