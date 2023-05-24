package com.awesome.testing.tests.cdp.remote;

import com.google.common.net.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.remote.http.Contents.utf8String;

public class GridNetworkInterceptorTest extends RemoteTest {

    private static final String MOCKED_RESPONSE = "That's mocked response";

    @BeforeEach
    public void setUp() {
        driver = new Augmenter().augment(driver);
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
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
