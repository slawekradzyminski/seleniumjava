package com.awesome.testing.api;

import com.awesome.testing.dto.UserDto;
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
    public void postSignUp(UserDto user) {
        RequestBody body = RequestBody.create(getJsonBody(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + "/users/signup")
                .post(body)
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            log.info("Received response {}", response);
            assertThat(response.code()).isEqualTo(201);
        }
    }

    @SneakyThrows
    private String getJsonBody(UserDto user) {
        String bodyAsString = OBJECT_MAPPER.writeValueAsString(user);
        log.info("Sending the following request body: {}", bodyAsString);
        return bodyAsString;
    }

}
