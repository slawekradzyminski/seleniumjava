package com.awesome.testing.api;

import com.awesome.testing.api.dto.login.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractApi {

    protected static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    protected static final String BASE_URL = "http://localhost:4001";
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected static final String AUTHORIZATION = "Authorization";

    protected String getAuthorizationHeader(String token) {
        return String.format("Bearer %s", token);
    }

    @SneakyThrows
    protected <T> T as(ResponseBody body, Class<T> clazz) {
        return OBJECT_MAPPER.readValue(body.string(), clazz);
    }

    @NotNull
    protected RequestBody bodyFrom(Object object) {
        return RequestBody.create(asString(object), JSON);
    }

    @SneakyThrows
    private String asString(Object object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }
}
