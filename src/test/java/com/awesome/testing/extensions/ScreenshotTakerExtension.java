package com.awesome.testing.extensions;

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
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
            log.info("Detected test failure. Attempt to create a screenshot");
            String baseFileName = getBaseFileName(extensionContext);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String userDirectory = System.getProperty("user.dir");
            File target = new File(userDirectory + "/target/" + baseFileName + ".png");
            Files.copy(screenshot.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            log.info("Screenshot created in {}", target);
        }
    }

    private String getBaseFileName(ExtensionContext context) {
        return context.getRequiredTestClass().getSimpleName() + "-"
                + context.getRequiredTestMethod().getName() + "-"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyy-HHmmss"));
    }

}
