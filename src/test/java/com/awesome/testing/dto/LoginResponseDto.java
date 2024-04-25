package com.awesome.testing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private String username;
    private String token;
    private String email;
    private String firstName;
    private String lastName;
    private Roles[] roles;

}
