package com.awesome.testing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

@Slf4j
public abstract class AbstractApi {

    protected static final String AUTHORIZATION = "Authorization";
    protected static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    protected static final String API_BASE_URL = "http://localhost:4001";
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Java Object to JSON
     */
    @SneakyThrows
    protected RequestBody bodyFrom(Object object) {
        String bodyAsString = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        log.info("Sending {}", bodyAsString);
        return RequestBody.create(bodyAsString, JSON);
    }

    /**
     * JSON to Java Object
     */
    @SneakyThrows
    @SuppressWarnings("ConstantConditions")
    protected  <T> T toDto(Response response, Class<T> expectedClass) {
        String responseBody = response.body().string();
        T parsedResponse = OBJECT_MAPPER.readValue(responseBody, expectedClass);
        log.info("Got {}", OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(parsedResponse));
        return parsedResponse;
    }

    protected String getAuthHeaderValue(String token) {
        return "Bearer " + token;
    }

}
