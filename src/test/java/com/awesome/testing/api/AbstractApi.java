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

    protected static final OkHttpClient OK_HTTP_CLIENT = HttpClientConfiguration.getOkHttpClient();
    protected static final MediaType JSON = MediaType.get("application/json");
    protected static final String BASE_URL = "http://localhost:4001";
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Java DTO -> JSON as string
     */
    @SneakyThrows
    protected RequestBody getBody(Object object) {
        String bodyAsString = OBJECT_MAPPER.writeValueAsString(object);
        return RequestBody.create(bodyAsString, JSON);
    }

    /**
     * OkHttp Response -> Java DTO
     */
    @SneakyThrows
    @SuppressWarnings("ConstantConditions")
    protected <T> T toDto(Response response, Class<T> expectedClass) {
        String responseAsString = response.body().string();
        return OBJECT_MAPPER.readValue(responseAsString, expectedClass);
    }

}
