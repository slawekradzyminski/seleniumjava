package com.awesome.testing.generators;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class UserGenerator {

    private static final Faker FAKER = new Faker();

    public static UserDto getRandomUser() {
        UserDto randomUser = UserDto.builder()
                .username(FAKER.internet().username())
                .password(FAKER.internet().password())
                .email(FAKER.internet().emailAddress())
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .build();

        log.info("Test runs with user data {}", randomUser);
        return randomUser;
    }

}
