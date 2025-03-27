package com.awesome.testing.generators;

import com.awesome.testing.http.dto.RegisterRequestDto;
import com.awesome.testing.http.dto.Roles;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@UtilityClass
public class UserGenerator {

    private static final Faker FAKER = new Faker();
    private static final int MIN_LENGTH = 4;
    private static final int MAX_RETRIES = 10;

    public static RegisterRequestDto getRandomUser() {
        String username = getValidValue(() -> FAKER.internet().username(), "username");
        String firstName = getValidValue(() -> FAKER.name().firstName(), "firstName");
        String lastName = getValidValue(() -> FAKER.name().lastName(), "lastName");

        RegisterRequestDto user = RegisterRequestDto.builder()
                .username(username)
                .password(FAKER.internet().password())
                .roles(List.of(Roles.ROLE_ADMIN, Roles.ROLE_CLIENT))
                .firstName(firstName)
                .lastName(lastName)
                .email(FAKER.internet().emailAddress())
                .build();

        log.info("Using user with details {} in test", user);
        return user;
    }

    private static String getValidValue(Supplier<String> generator, String fieldName) {
        for (int i = 0; i < MAX_RETRIES; i++) {
            String value = generator.get();
            if (value != null && value.length() >= MIN_LENGTH) {
                return value;
            }
        }
        String fallback = fieldName + "_test";
        log.warn("Could not generate valid '{}' after {} retries. Falling back to '{}'", fieldName, MAX_RETRIES, fallback);
        return fallback;
    }

}
