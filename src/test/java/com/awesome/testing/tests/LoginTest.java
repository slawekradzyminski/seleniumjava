package com.awesome.testing.tests;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends TestArenaTest {

    @Test
    public void shouldSuccessfullyLogin() {
        loginPage.attemptLogin("administrator@testarena.pl", "sumXQQ72$L")
                .openProjects()
                .waitForTestAreaToLoad();

        assertThat(1).isEqualTo(2);
    }

    @Test
    public void screenshotInStepTest() {
        driver.get("https://www.google.com");
        step1();
        step2();
        step3();
    }

    @Step("Step 1")
    public void step1(){
        System.out.println("step 1");
    }
    @Step("Step 2 with screenshot")
    public void step2(){
        System.out.println("step 2");
        screenshot();
    }
    @Step("Step 3")
    public void step3(){
        System.out.println("step 3");
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
