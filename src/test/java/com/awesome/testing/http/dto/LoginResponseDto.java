package com.awesome.testing.http.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDto {
    private String token;
    private String username;
    private String email;
    // Other fields can be added as needed
} 