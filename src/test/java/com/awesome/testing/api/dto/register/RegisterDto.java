package com.awesome.testing.api.dto.register;

import com.awesome.testing.generator.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class RegisterDto {

    String email;
    String firstName;
    String lastName;
    String password;
    Roles[] roles;
    String username;

    public static RegisterDto from(User user) {
        return RegisterDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}