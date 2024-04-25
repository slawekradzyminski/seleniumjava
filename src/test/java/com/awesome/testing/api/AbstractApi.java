package com.awesome.testing.api;

import com.awesome.testing.api.config.HttpClientConfiguration;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.awesome.testing.config.ObjectMapperProvider.OBJECT_MAPPER;

@Slf4j
public abstract class AbstractApi {

    protected static final OkHttpClient OK_HTTP_CLIENT = HttpClientConfiguration.getOkHttpClient();
    protected static final MediaType JSON = MediaType.get("application/json");
    protected static final String BASE_URL = "http://localhost:4001";
    protected static final String AUTHORIZATION = "Authorization";

    protected static RequestBody getRequestBody(Object object) {
        return RequestBody.create(getJsonBody(object), JSON);
    }

    @SneakyThrows
    @SuppressWarnings("all")
    protected static  <T> T toDto(Response response, Class<T> expectedDto) {
        return OBJECT_MAPPER.readValue(response.body().string(), expectedDto);
    }

    protected static String getAuthToken(String token) {
        return String.format("Bearer %s", token);
    }

    @SneakyThrows
    private static String getJsonBody(Object object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

}
