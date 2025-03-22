package com.awesome.testing.http;

import com.awesome.testing.http.dto.LoginRequestDto;
import com.awesome.testing.http.dto.LoginResponseDto;
import com.awesome.testing.http.factory.LoginRequestFactory;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

@UtilityClass
public class LoginClient extends AbstractHttpClient {

    private static final String LOGIN_ENDPOINT = "/users/signin";

    @SneakyThrows
    public static String loginAsAdmin() {
        LoginRequestDto loginRequestDto = LoginRequestFactory.loginRequest("admin", "admin");
        String jsonBody = getObjectMapper().writeValueAsString(loginRequestDto);
        RequestBody requestBody = RequestBody.create(jsonBody, JSON_MEDIA_TYPE);

        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_ENDPOINT)
                .post(requestBody)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code: " + response);
            }

            String responseBody = Objects.requireNonNull(response.body()).string();
            LoginResponseDto loginResponseDto = getObjectMapper().readValue(responseBody, LoginResponseDto.class);
            
            if (loginResponseDto.getToken() == null || loginResponseDto.getToken().isEmpty()) {
                throw new IOException("JWT token not found in response: " + responseBody);
            }

            return loginResponseDto.getToken();
        }
    }
} 