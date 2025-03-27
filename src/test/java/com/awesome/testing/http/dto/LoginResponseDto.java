package com.awesome.testing.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    String username;
    String token;
    String email;
    String firstName;
    String lastName;
    List<Roles> roles;

}
