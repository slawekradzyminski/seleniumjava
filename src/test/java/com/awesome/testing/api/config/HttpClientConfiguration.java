package com.awesome.testing.api.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Slf4j
public class HttpClientConfiguration {

    public static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                message -> log.info("HTTP: {}", parseMessage(message)));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @SuppressWarnings("all")
    private static String parseMessage(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object json = mapper.readValue(message, Object.class);
            String requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            return "Body: \n" + requestBody;
        } catch (JsonProcessingException e) {
            return message;
        }
    }

}
