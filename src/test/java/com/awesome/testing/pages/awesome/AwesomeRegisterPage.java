package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AwesomeRegisterPage extends AbstractBasePage {

    @FindBy(css = "h2")
    private WebElement header;

    public AwesomeRegisterPage(WebDriver driver) {
        super(driver);
    }

    public void assertHeader() {
        wait.until((driver) -> header.getText().contains("Register"));
    }
}
