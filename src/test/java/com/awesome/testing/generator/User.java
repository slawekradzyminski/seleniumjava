package com.awesome.testing.generator;

import com.awesome.testing.api.dto.register.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    Roles[] roles;

}
