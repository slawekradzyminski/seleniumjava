package com.awesome.testing.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {

    private String username;
    private String token;
    private String email;
    private String firstName;
    private String lastName;
    private Roles[] roles;

}
