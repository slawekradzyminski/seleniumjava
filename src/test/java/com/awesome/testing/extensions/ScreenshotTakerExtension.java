package com.awesome.testing.extensions;

import io.qameta.allure.Allure;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ScreenshotTakerExtension implements AfterTestExecutionCallback {

    @Setter
    private static WebDriver driver;

    @SneakyThrows
    public void afterTestExecution(ExtensionContext extensionContext) {
        if (extensionContext.getExecutionException().isPresent()) {
            // 1. Log
            log.info("Detected test failure. Attempting to create a screenshot.");

            // 2. Capture screenshot file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // 3. Build a filename
            String baseFileName = getBaseFileName(extensionContext);
            String userDirectory = System.getProperty("user.dir");
            File target = new File(userDirectory + "/target/" + baseFileName + ".png");

            // 4. Copy the screenshot file to target/
            Files.copy(screenshot.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            log.info("Screenshot saved to {}", target.getAbsolutePath());

            // 5. Attach to Allure
            //    We must read the file bytes and pass them to Allure.addAttachment
            try (InputStream is = new FileInputStream(target)) {
                Allure.addAttachment("Failure Screenshot [" + baseFileName + "]", "image/png", is, "png");
            }
        }
    }

    private String getBaseFileName(ExtensionContext context) {
        return context.getRequiredTestClass().getSimpleName() + "-"
                + context.getRequiredTestMethod().getName() + "-"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyy-HHmmss"));
    }
}
