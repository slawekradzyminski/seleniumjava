package com.awesome.testing.api.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FailedLoginDto {

    String timestamp;
    String error;
    String message;
    String path;
    int status;

}
