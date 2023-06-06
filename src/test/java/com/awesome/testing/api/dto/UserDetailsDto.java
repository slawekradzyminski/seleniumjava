package com.awesome.testing.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDto {

    int id;
    String firstName;
    String lastName;
    Roles[] roles;
    String email;
    String username;

}
