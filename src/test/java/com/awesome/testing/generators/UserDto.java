package com.awesome.testing.generators;

import com.github.javafaker.Faker;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Builder
@Slf4j
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {

    private static final Faker FAKER = new Faker();

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public static UserDto getRandomUser() {
        UserDto userDto = UserDto.builder()
                .username(FAKER.name().username())
                .password(FAKER.internet().password())
                .email(FAKER.internet().emailAddress())
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .build();

        log.info("Using user {}", userDto);
        return userDto;
    }

}
