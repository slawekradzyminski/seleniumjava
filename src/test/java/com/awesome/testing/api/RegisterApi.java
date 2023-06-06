package com.awesome.testing.api;

import com.awesome.testing.api.dto.RegisterResponseDto;
import com.awesome.testing.generator.dto.User;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterApi extends AbstractApi {

    @SneakyThrows
    public RegisterResponseDto register(User user) {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/users/signup")
                .post(bodyFrom(user))
                .build();

        Response response = HTTP_CLIENT.newCall(request).execute();
        return toDto(response, RegisterResponseDto.class);
    }

}
