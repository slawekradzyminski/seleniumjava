package com.awesome.testing.http.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDto {

    String username;
    String password;

}
