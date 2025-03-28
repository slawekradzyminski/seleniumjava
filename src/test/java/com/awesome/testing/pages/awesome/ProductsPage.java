package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import com.awesome.testing.pages.awesome.components.LoggedInHeader;
import com.awesome.testing.pages.awesome.components.Toast;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.ThreadLocalRandom;

import static org.awaitility.Awaitility.await;

public class ProductsPage extends BasePage {

    private static final By PRODUCT_CARD = By.cssSelector("[data-testid=product-item]");

    @Getter
    private Toast toast;

    @Getter
    private LoggedInHeader loggedInHeader;

    public ProductsPage(WebDriver driver) {
        super(driver);
        toast = new Toast(driver);
        loggedInHeader = new LoggedInHeader(driver);
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

    public void verifyProductCartUpdated(int productIndex) {
        WebElement productCard = driver.findElements(PRODUCT_CARD).get(productIndex);
        wait.until(driver -> productCard.findElement(By.xpath("//button[text()='Remove']")).isDisplayed());
        wait.until(driver -> productCard.findElement(By.xpath("//button[text()='Update Cart']")).isDisplayed());
        wait.until(driver -> productCard.findElement(By.className("text-blue-600")).getText().equals("1 in cart"));
    }

    public String getProductName(int productIndex) {
        WebElement productCard = driver.findElements(PRODUCT_CARD).get(productIndex);
        return productCard.findElement(By.cssSelector("[data-testid=product-name]")).getText();
    }
}
