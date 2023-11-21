package com.awesome.testing.pages.awesome;

import com.awesome.testing.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class AwesomeRegisterPage extends BasePage {

    @FindBy(css = "h2")
    private WebElement header;

    public AwesomeRegisterPage(WebDriver driver) {
        super(driver);
    }

    public void verifyHeader() {
        assertThat(header.getText()).isEqualTo("Register");
    }

}
