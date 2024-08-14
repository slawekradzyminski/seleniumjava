package com.awesome.testing.tests.bidi.cdp.local;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class NipResponseDto {

    int userId;
    int id;
    String title;
    String body;

}
