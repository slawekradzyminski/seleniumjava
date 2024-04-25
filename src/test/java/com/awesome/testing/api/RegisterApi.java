package com.awesome.testing.api;

import com.awesome.testing.dto.UserDto;
import lombok.SneakyThrows;
import okhttp3.*;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterApi extends AbstractApi {

    @SneakyThrows
    public static void postSignUp(UserDto user) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/signup")
                .post(getRequestBody(user))
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            assertThat(response.code()).isEqualTo(201);
        }
    }

}
