package com.awesome.testing.extensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Slf4j
public class NameLoggerExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        log.info("Starting test {}#{}",
                extensionContext.getTestClass().get().getSimpleName(),
                extensionContext.getTestMethod().get().getName());
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        log.info("Finished test {}#{}",
                extensionContext.getTestClass().get().getSimpleName(),
                extensionContext.getTestMethod().get().getName());
    }

}