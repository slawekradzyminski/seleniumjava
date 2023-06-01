package com.awesome.testing.api.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {

    String email;
    String firstName;
    String lastName;
    String password;
    Roles[] roles;
    String username;
}