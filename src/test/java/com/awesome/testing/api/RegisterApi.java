package com.awesome.testing.api;

import com.awesome.testing.dto.UserDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class RegisterApi extends AbstractApi {

    @SneakyThrows
    public void postSignUp(UserDto user) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/signup")
                .post(getRequestBody(user))
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            log.info("Received response {}", response);
            assertThat(response.code()).isEqualTo(201);
        }
    }

}
