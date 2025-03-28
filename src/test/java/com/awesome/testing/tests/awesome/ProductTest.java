package com.awesome.testing.tests.awesome;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.pages.awesome.ProductsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest extends LoggedInSeleniumTest {

    @BeforeEach
    public void setUp() {
        driver.navigate().to(ConfigProvider.get("frontend.url") + "/products");
    }

    @Test
    public void shouldAddProductToBasket() {
        ProductsPage productsPage = new ProductsPage(driver);
        int productIndex = productsPage
                .verifyIsLoaded()
                .addRandomProductToBasketAndReturnItsIndex();

        productsPage
                .verifyButtonsChanged(productIndex);

        driver.quit();
    }

}
