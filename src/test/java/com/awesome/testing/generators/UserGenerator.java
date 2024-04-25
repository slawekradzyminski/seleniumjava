package com.awesome.testing.generators;

import com.awesome.testing.dto.Roles;
import com.awesome.testing.dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class UserGenerator {

    private static final Faker FAKER = new Faker();

    public static UserDto getRandomUser() {
        UserDto randomUser = UserDto.builder()
                .username(FAKER.internet().username())
                .password(FAKER.internet().password())
                .email(FAKER.internet().emailAddress())
                .firstName(getValidTestData(() -> FAKER.name().firstName()))
                .lastName(getValidTestData(() -> FAKER.name().lastName()))
                .roles(new Roles[]{Roles.ROLE_ADMIN, Roles.ROLE_CLIENT})
                .build();

        log.info("Test runs with user data {}", randomUser);
        return randomUser;
    }

    private static String getValidTestData(Supplier<String> function) {
        String firstName = function.get();
        int attempt = 1;
        while (firstName.length() < 4 && attempt <= 20) {
            firstName = function.get();
            attempt++;
            log.info("Failed to draw valid first name. Attempt {}", attempt);
        }

        if (firstName.length() < 4) {
            throw new IllegalArgumentException("Failed to draw valid test data");
        }
        return firstName;
    }

}
