package com.awesome.testing.api;

import com.awesome.testing.api.dto.LoginRequestDto;
import com.awesome.testing.api.dto.LoginResponseDto;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginApi extends AbstractApi {

    @SneakyThrows
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/signin")
                .post(getBody(loginRequestDto))
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            assertThat(response.code()).isEqualTo(200);
            return toDto(response, LoginResponseDto.class);
        }
    }

}
