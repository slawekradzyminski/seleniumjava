package com.awesome.testing.api;

import com.awesome.testing.api.dto.login.LoginDto;
import com.awesome.testing.api.dto.login.LoginResponseDto;
import com.awesome.testing.generator.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

@Slf4j
public class ApiLogin extends AbstractApi {

    public static final String ENDPOINT = "/users/signin";

    @SneakyThrows
    public LoginResponseDto login(LoginDto loginDto) {
        Request request = new Request.Builder()
                .url(BASE_URL + ENDPOINT)
                .post(bodyFrom(loginDto))
                .build();
        log.info("Logging in with body {}", loginDto);
        Response response = HTTP_CLIENT.newCall(request).execute();
        log.info("Logging in returned {}", response);
        return as(response.body(), LoginResponseDto.class);
    }

    public LoginResponseDto login(User user) {
        return login(new LoginDto(user.getUsername(), user.getPassword()));
    }

}
