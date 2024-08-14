package com.awesome.testing.tests.bidi.cdp.local;

import com.awesome.testing.tests.bidi.BidiTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.net.MediaType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpHandler;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.remote.http.Contents.utf8String;

public class NetworkInterceptorTest extends BidiTest {


//    @BeforeEach
//    public void setUp() {
//        driver.get("https://the-internet.herokuapp.com/redirector");
//        mockRequestWhichIsTriggeredByClickingOnHereLink();
//    }

    @Test
    public void networkInterceptor() {
        // when
        driver.findElement(By.linkText("here")).click();

        // then
//        assertThat(driver.getPageSource()).contains(MOCKED_RESPONSE);
    }

    @Test
    public void jsonPlaceholder() {
        driver.get("https://jsonplaceholder.typicode.com/");
        mockDefinition();
        driver.findElement(By.linkText("/posts/1")).click();


        assertThat(true).isTrue();
    }

    @SuppressWarnings("resource")
    private void mockDefinition() {
        Route route = Route
                .matching(NetworkInterceptorTest::myDefinitonWhatToMock)
                .to(NetworkInterceptorTest::myDefinitionOfResponse);

        new NetworkInterceptor(driver, route);
    }

    @SneakyThrows
    private static HttpHandler myDefinitionOfResponse() {
        NipResponseDto myResponse = NipResponseDto.builder()
                .userId(1)
                .id(1)
                .title("Basia")
                .body("Slawek")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String myResponseAsString = objectMapper.writeValueAsString(myResponse);

        return req -> new HttpResponse()
                .setStatus(200)
                .addHeader("Content-Type", MediaType.JSON_UTF_8.toString())
                .setContent(utf8String(myResponseAsString));
    }

    private static boolean myDefinitonWhatToMock(HttpRequest req) {
        return req.getUri().contains("posts/1");
    }


}
