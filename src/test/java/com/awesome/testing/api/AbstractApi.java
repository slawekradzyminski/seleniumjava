package com.awesome.testing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class AbstractApi {

    protected static final String AUTHORIZATION = "Authorization";

    protected String getAuthorizationHeader(String token) {
        return String.format("Bearer %s", token);
    }

    @SneakyThrows
    protected <T> T as(Response response, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBody responseBody = response.body();
        return objectMapper.readValue(responseBody.string(), clazz);
    }
}
