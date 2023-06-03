package com.awesome.testing.api;

import com.awesome.testing.api.dto.register.RegisterDto;
import com.awesome.testing.api.dto.register.RegisterResponseDto;
import com.awesome.testing.generator.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class ApiRegister extends AbstractApi {

    public static final String ENDPOINT = "/users/signup";

    @SneakyThrows
    public RegisterResponseDto register(User user) {
        Request request = new Request.Builder()
                .url(BASE_URL + ENDPOINT)
                .post(bodyFrom(RegisterDto.from(user)))
                .build();
        log.info("Registering user {}", user);
        Response response = HTTP_CLIENT.newCall(request).execute();
        log.info("Registration returned {}", response);
        return as(response.body(), RegisterResponseDto.class);
    }

}
