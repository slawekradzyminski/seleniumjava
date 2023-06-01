package com.awesome.testing.api.dto.userdetails;

import com.awesome.testing.api.dto.register.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class UserDetailsDto {

    int id;
    String username;
    String firstName;
    String lastName;
    String email;
    Roles[] roles;

}
