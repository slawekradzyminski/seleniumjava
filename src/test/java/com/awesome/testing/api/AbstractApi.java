package com.awesome.testing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.*;

public abstract class AbstractApi {

    protected static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    protected static final String API_BASE_URL = "http://localhost:4001";
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Java Object to JSON
     */
    @SneakyThrows
    protected RequestBody bodyFrom(Object object) {
        String bodyAsString = OBJECT_MAPPER.writeValueAsString(object);
        return RequestBody.create(bodyAsString, JSON);
    }

    /**
     * JSON to Java Object
     */
    @SneakyThrows
    @SuppressWarnings("ConstantConditions")
    protected  <T> T toDto(Response response, Class<T> expectedClass) {
        ResponseBody responseBody = response.body();
        return OBJECT_MAPPER.readValue(responseBody.string(), expectedClass);
    }

}
