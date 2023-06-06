package com.awesome.testing.extensions;

import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotTaker implements AfterTestExecutionCallback {

    private static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        ScreenshotTaker.driver = driver;
    }

    @SneakyThrows
    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            String baseFileName = getBaseFileName(context);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String userDirectory = System.getProperty("user.dir");
            File target = new File(userDirectory + "/target/" + baseFileName + ".png");
            try (InputStream is = Files.newInputStream(screenshot.toPath())) {
                Allure.addAttachment(baseFileName, is);
            }
            Files.copy(screenshot.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private String getBaseFileName(ExtensionContext context) {
        return context.getRequiredTestClass().getSimpleName() + "-"
                + context.getRequiredTestMethod().getName()
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("-yyMMdd-HHmmss"));
    }

}
