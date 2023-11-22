package com.awesome.testing.api;

import com.awesome.testing.api.dto.UserResponseDto;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDetailsApi extends AbstractApi {

    @SneakyThrows
    public UserResponseDto getUserDetails(String username, String token) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/users/" + username)
                .header(AUTHORIZATION, getAuthTokenValue(token))
                .get()
                .build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            assertThat(response.code()).isEqualTo(200);
            return toDto(response, UserResponseDto.class);
        }
    }



}
