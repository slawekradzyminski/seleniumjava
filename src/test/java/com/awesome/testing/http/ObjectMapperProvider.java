package com.awesome.testing.http;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperProvider {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

}
