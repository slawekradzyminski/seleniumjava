package com.awesome.testing.api;

import com.awesome.testing.dto.LoginRequestDto;
import com.awesome.testing.dto.LoginResponseDto;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginApi extends AbstractApi {

    @SneakyThrows
    public static LoginResponseDto signIn(String username, String password) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/signin")
                .post(getRequestBody(getLoginRequestDto(username, password)))
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            assertThat(response.code()).isEqualTo(200);
            return toDto(response, LoginResponseDto.class);
        }
    }

    private static LoginRequestDto getLoginRequestDto(String username, String password) {
        return LoginRequestDto.builder()
                .username(username)
                .password(password)
                .build();
    }

}
