package com.awesome.testing.api.dto;

import com.awesome.testing.generators.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Roles[] roles;

}
