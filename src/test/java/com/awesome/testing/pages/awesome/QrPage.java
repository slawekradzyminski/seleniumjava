package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.awaitility.Awaitility.await;

public class QrPage extends BasePage {

    private static final By QR_CODE = By.cssSelector("[alt='Generated QR Code']");

    public QrPage(WebDriver driver) {
        super(driver);
    }

    @SuppressWarnings("ConstantConditions")
    public void verifyIsLoaded() {
        await().ignoreException(NullPointerException.class)
                .until(() -> driver.getPageSource().contains("QR Code Generator"));
    }

    @Step("Generate QR code")
    public QrPage generateQr(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("qr-text")));
        driver.findElement(By.id("qr-text")).sendKeys(text);
        driver.findElement(By.xpath("//button[text()='Generate QR Code']")).click();
        return this;
    }

    public QrPage assertQrCodeDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(QR_CODE));
        return this;
    }

    @Step("Clear QR code")
    public QrPage clearQrCode() {
        driver.findElement(By.xpath("//button[text()='Clear']")).click();
        return this;
    }

    public void assertQrCodeNotDisplayed() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(QR_CODE));
    }
}
