package com.awesome.testing.generators;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

}
