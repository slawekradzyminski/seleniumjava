package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AwesomeHomePage extends AbstractBasePage {

    @FindBy(css = "h1")
    private WebElement header;

    public AwesomeHomePage(WebDriver driver) {
        super(driver);
    }

    public void assertThatHeaderContains(String text) {
        wait.until((driver) -> header.getText().contains(text));
    }

}
