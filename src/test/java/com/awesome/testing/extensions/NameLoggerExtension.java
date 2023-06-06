package com.awesome.testing.extensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class NameLoggerExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("Starting test {}, ", context.getTestMethod().get());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        log.info("Finished test {}, ", context.getTestMethod().get());
    }
}
