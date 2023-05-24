package com.awesome.testing.tests.cdp.local;

import com.awesome.testing.tests.LocalTest;
import com.google.common.net.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.remote.http.Contents.utf8String;

public class NetworkInterceptorTest extends LocalTest {

    private static final String MOCKED_RESPONSE = "That's mocked response";

    @BeforeEach
    public void setUp() {
        driver.get("https://the-internet.herokuapp.com/redirector");
        mockRequestWhichIsTriggeredByClickingOnHereLink();
    }

    @Test
    public void networkInterceptor() {
        // when
        driver.findElement(By.linkText("here")).click();

        // then
        assertThat(driver.getPageSource()).contains(MOCKED_RESPONSE);
    }

    @SuppressWarnings("resource")
    private void mockRequestWhichIsTriggeredByClickingOnHereLink() {
        new NetworkInterceptor(
                driver,
                Route.matching(req -> req.getUri().contains("status_codes"))
                        .to(() -> req -> new HttpResponse()
                                .setStatus(200)
                                .addHeader("Content-Type", MediaType.HTML_UTF_8.toString())
                                .setContent(utf8String(MOCKED_RESPONSE))));
    }


}
