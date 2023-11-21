package com.awesome.testing.api;

import com.awesome.testing.generators.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class RegisterApi {

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json");
    private static final String BASE_URL = "http://localhost:4001";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @SneakyThrows
    public String bodyFrom(Object object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    @SneakyThrows
    public void register(UserDto userDto) {
        RequestBody body = RequestBody.create(bodyFrom(userDto), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/signup")
                .post(body)
                .build();
        log.info("Sending the following request: {}", request);
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            log.info("Received the following response: {}", response);
            assertThat(response.code()).isEqualTo(201);
        }
    }

}
