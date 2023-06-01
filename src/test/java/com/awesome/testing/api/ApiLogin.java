package com.awesome.testing.api;

import com.awesome.testing.api.dto.login.LoginDto;
import com.awesome.testing.api.dto.login.LoginResponseDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

@Slf4j
public class ApiLogin extends AbstractApi {

    @SneakyThrows
    public LoginResponseDto login(LoginDto loginDto) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String json = """
                {
                "username": "admin",
                "password": "admin"
                }
                """;

        RequestBody body = RequestBody.create(json, JSON); // new
        Request request = new Request.Builder()
                .url("http://localhost:4001/users/signin")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return as(response, LoginResponseDto.class);
    }
}
