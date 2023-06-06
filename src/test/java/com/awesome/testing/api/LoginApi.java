package com.awesome.testing.api;

import com.awesome.testing.api.dto.LoginDto;
import com.awesome.testing.api.dto.LoginResponseDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class LoginApi extends AbstractApi {

    @SneakyThrows
    public LoginResponseDto login(LoginDto loginDto) {
        String url = API_BASE_URL + "/users/signin";
        log.info("Sending POST to {}", url);
        Request request = new Request.Builder()
                .url(url)
                .post(bodyFrom(loginDto))
                .build();

        Response response = HTTP_CLIENT.newCall(request).execute();
        return toDto(response, LoginResponseDto.class);
    }

    @SneakyThrows
    public LoginResponseDto login(String username, String password) {
        return login(new LoginDto(username, password));
    }

}
