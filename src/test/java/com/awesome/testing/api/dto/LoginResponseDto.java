package com.awesome.testing.api.dto;

import com.awesome.testing.generators.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private Roles[] roles;

}
