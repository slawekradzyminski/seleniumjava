package com.awesome.testing.api;

import com.awesome.testing.api.dto.login.LoginDto;
import com.awesome.testing.api.dto.login.LoginResponseDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

@Slf4j
public class ApiLogin extends AbstractApi {

    @SneakyThrows
    public LoginResponseDto login(LoginDto loginDto) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/signin")
                .post(bodyFrom(loginDto))
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return as(response, LoginResponseDto.class);
    }

}
