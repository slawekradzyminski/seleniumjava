package com.awesome.testing.generator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    String username;
    String password;
    String email;
    String firstName;
    String lastName;

}
