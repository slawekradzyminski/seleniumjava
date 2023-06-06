package com.awesome.testing.api;

import com.awesome.testing.api.dto.RegisterResponseDto;
import com.awesome.testing.generator.dto.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class RegisterApi extends AbstractApi {

    @SneakyThrows
    public RegisterResponseDto register(User user) {
        String url = API_BASE_URL + "/users/signup";
        log.info("Sending POST to {}", url);
        Request request = new Request.Builder()
                .url(url)
                .post(bodyFrom(user))
                .build();

        Response response = HTTP_CLIENT.newCall(request).execute();
        return toDto(response, RegisterResponseDto.class);
    }

}
