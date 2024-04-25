package com.awesome.testing.api;

import com.awesome.testing.dto.LoginRequestDto;
import com.awesome.testing.dto.LoginResponseDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class LoginApi extends AbstractApi {

    @SuppressWarnings("all")
    @SneakyThrows
    public LoginResponseDto signIn(String username, String password) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/signin")
                .post(getRequestBody(getLoginRequestDto(username, password)))
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            log.info("Received response {}", response);
            assertThat(response.code()).isEqualTo(200);
            return toDto(response, LoginResponseDto.class);
        }
    }

    private LoginRequestDto getLoginRequestDto(String username, String password) {
        return LoginRequestDto.builder()
                .username(username)
                .password(password)
                .build();
    }

}
