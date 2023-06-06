package com.awesome.testing.api;

import com.awesome.testing.api.dto.UserDetailsDto;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

public class UserApi extends AbstractApi {

    @SneakyThrows
    public UserDetailsDto getUserDetails(String username, String token) {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/users/" + username)
                .header(AUTHORIZATION, getAuthHeaderValue(token))
                .get()
                .build();

        Response response = HTTP_CLIENT.newCall(request).execute();
        return toDto(response, UserDetailsDto.class);
    }

}
