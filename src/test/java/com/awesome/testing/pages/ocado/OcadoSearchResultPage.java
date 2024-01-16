package com.awesome.testing.pages.ocado;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static org.awaitility.Awaitility.await;

public class OcadoSearchResultPage extends BasePage {

    @FindBy(id = "search")
    private WebElement searchInput;

    public OcadoSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public OcadoSearchResultPage addToTrolley() {
        driver.findElement(By.className("add-to-trolley")).click();
        return this;
    }

    public OcadoSearchResultPage searchFor(String searchTerm) {
        searchInput.sendKeys(searchTerm + Keys.ENTER);
        waitForReload(searchTerm);
        return new OcadoSearchResultPage(driver);
    }

    private void waitForReload(String searchTerm) {
        await().until(() -> {
            try {
                return driver.findElements(By.className("fop-title"))
                        .stream()
                        .map(WebElement::getText)
                        .anyMatch(it -> it.toLowerCase().contains(searchTerm.toLowerCase()));
            } catch (Exception e) {
                return false;
            }
        });
    }

}
