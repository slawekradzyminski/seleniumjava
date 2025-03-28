package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class ProductsPage extends BasePage {

    private static final By PRODUCT_CARD = By.cssSelector("[data-testid=product-item]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage verifyIsLoaded() {
        await().until(() -> driver.findElements(PRODUCT_CARD).size() > 5);
        return this;
    }

    public int addRandomProductToBasketAndReturnItsIndex() {
        int numberOfProducts = driver.findElements(PRODUCT_CARD).size();
        int randomIndex = getRandomIndex(numberOfProducts);
        driver.findElements(By.xpath("//button[text()='Add to Cart']")).get(randomIndex).click();
        return randomIndex;
    }

    private int getRandomIndex(int upperBoundExclusive) {
        return ThreadLocalRandom.current().nextInt(upperBoundExclusive);
    }

    public void verifyButtonsChanged(int productIndex) {
        WebElement productCard = driver.findElements(PRODUCT_CARD).get(productIndex);
        wait.until(driver -> productCard.findElement(By.xpath("//button[text()='Remove']")));
        wait.until(driver -> productCard.findElement(By.xpath("//button[text()='Update Cart']")));
    }
}
