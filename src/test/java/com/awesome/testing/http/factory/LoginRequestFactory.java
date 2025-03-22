package com.awesome.testing.http.factory;

import com.awesome.testing.http.dto.LoginRequestDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LoginRequestFactory {

    public static LoginRequestDto loginRequest(String username, String password) {
        return LoginRequestDto.builder()
                .username(username)
                .password(password)
                .build();
    };

}
