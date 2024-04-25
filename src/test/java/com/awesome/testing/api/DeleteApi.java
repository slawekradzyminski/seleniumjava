package com.awesome.testing.api;

import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteApi extends AbstractApi {


    @SneakyThrows
    public static void deleteUser(String username, String token) {
        Request request = new Request.Builder()
                .url(String.format("%s/users/%s", BASE_URL, username))
                .delete()
                .header(AUTHORIZATION, getAuthToken(token))
                .build();

        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            assertThat(response.code()).isEqualTo(204);
        }
    }

}
