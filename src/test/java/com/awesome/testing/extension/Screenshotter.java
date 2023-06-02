package com.awesome.testing.extension;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Slf4j
public class Screenshotter implements AfterTestExecutionCallback {
    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        Screenshotter.driver = driver;
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) { // if the test execution has failed
            String baseFileName = context.getRequiredTestClass().getSimpleName() + "-"
                    + context.getRequiredTestMethod().getName()
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyMMdd-HHmmss"));
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try (InputStream is = Files.newInputStream(scrFile.toPath())) {
                Allure.addAttachment(baseFileName, is);
            }
        }
    }
}
