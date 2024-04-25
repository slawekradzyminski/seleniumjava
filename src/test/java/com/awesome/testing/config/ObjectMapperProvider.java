package com.awesome.testing.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMapperProvider {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

}
