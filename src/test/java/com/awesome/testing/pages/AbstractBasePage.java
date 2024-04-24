package com.awesome.testing.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractBasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @SneakyThrows
    protected <T extends AbstractBasePage> T newInstanceOf(Class<T> expectedPage) {
        return expectedPage.getDeclaredConstructor(WebDriver.class).newInstance(driver);
    }

}
