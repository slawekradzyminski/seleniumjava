package com.awesome.testing.tests.bidi.cdp.local;

import com.awesome.testing.tests.bidi.BidiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;

import java.net.URI;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicAuthTest extends BidiTest {

    private static final String ADMIN = "admin";

    @BeforeEach
    public void setupBasicAuth() {
        Predicate<URI> uriPredicate = uri -> uri.getHost().contains("the-internet.herokuapp.com");
        ((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of(ADMIN, ADMIN));
    }

    @Test
    public void basicAuth() {
        // when
        driver.get("https://the-internet.herokuapp.com/basic_auth");

        // then
        assertThat(driver.findElement(By.cssSelector(".example p")).getText()).contains("Congratulations");
    }

}
