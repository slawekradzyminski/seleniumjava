package com.awesome.testing.http;

import com.awesome.testing.http.dto.RegisterRequestDto;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import okhttp3.*;

import static com.awesome.testing.http.ObjectMapperProvider.getObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

@UtilityClass
public class RegisterApi extends BaseApi {

    private static final String SIGNUP_URL = apiUrl("/users/signup");

    @SneakyThrows
    public static void register(RegisterRequestDto user) {
        String body = getObjectMapper().writeValueAsString(user);

        Request request = new Request.Builder()
                .url(SIGNUP_URL)
                .post(RequestBody.create(body, JSON_MEDIA_TYPE))
                .build();

        try (Response response = getClient().newCall(request).execute()) {
            assertThat(response.code()).isEqualTo(201);
        }
    }

}
