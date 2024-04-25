package com.awesome.testing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

@Slf4j
public abstract class AbstractApi {

    protected static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    protected static final MediaType JSON = MediaType.get("application/json");
    protected static final String BASE_URL = "http://localhost:4001";
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected static RequestBody getRequestBody(Object object) {
        return RequestBody.create(getJsonBody(object), JSON);
    }

    @SneakyThrows
    @SuppressWarnings("all")
    protected static  <T> T toDto(Response response, Class<T> expectedDto) {
        return OBJECT_MAPPER.readValue(response.body().string(), expectedDto);
    }

    @SneakyThrows
    private static String getJsonBody(Object object) {
        String bodyAsString = OBJECT_MAPPER.writeValueAsString(object);
        log.info("Sending the following request body: {}", bodyAsString);
        return bodyAsString;
    }

}
