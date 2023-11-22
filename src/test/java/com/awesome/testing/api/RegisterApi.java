package com.awesome.testing.api;

import com.awesome.testing.generators.UserDto;
import lombok.SneakyThrows;
import okhttp3.*;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterApi extends AbstractApi {

    @SneakyThrows
    public void register(UserDto userDto) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/signup")
                .post(getBody(userDto))
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            assertThat(response.code()).isEqualTo(201);
        }
    }



}
