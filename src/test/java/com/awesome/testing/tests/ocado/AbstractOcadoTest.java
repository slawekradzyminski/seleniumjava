package com.awesome.testing.tests.ocado;

import com.awesome.testing.pages.ocado.OcadoSearchResultPage;
import com.awesome.testing.tests.AbstractSeleniumTest;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

public class AbstractOcadoTest extends AbstractSeleniumTest {

    protected OcadoSearchResultPage browsePage;

    @BeforeEach
    public void navigate() {
        driver.get("https://www.ocado.com/browse");
        driver.manage().window().setSize(new Dimension(1280, 1024));
        browsePage = new OcadoSearchResultPage(driver);
        acceptCookies();
    }

    private void acceptCookies() {
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.navigate().refresh();
    }

}
