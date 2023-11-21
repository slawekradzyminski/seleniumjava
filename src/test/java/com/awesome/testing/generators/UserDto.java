package com.awesome.testing.generators;

import com.github.javafaker.Faker;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

@Builder
@Slf4j
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserDto {

    private static final Faker FAKER = new Faker();

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Roles[] roles;

    public static UserDto.UserDtoBuilder getRandomUserBuilder() {
        return UserDto.builder()
                .username(draw(() -> FAKER.name().username()))
                .password(draw(() -> FAKER.internet().password()))
                .email(FAKER.internet().emailAddress())
                .firstName(draw(() -> FAKER.name().firstName()))
                .lastName(draw(() -> FAKER.name().lastName()))
                .roles(new Roles[]{Roles.ROLE_ADMIN, Roles.ROLE_CLIENT});
    }

    public static UserDto getRandomUser() {
        UserDto userDto = getRandomUserBuilder().build();
        log.info("Using user {}", userDto);
        return userDto;
    }

    @SneakyThrows
    private static String draw(Supplier<String> supplier) {
        String randomString = supplier.get();
        int i = 1;
        while (randomString.length() < 4 && i <= 50) {
            log.warn("Failed to draw correct test data. Retry {}", i);
            randomString = supplier.get();
            i++;
        }

        if (i == 51) {
            throw new FailedToDrawValidTestDataException();
        }
        return randomString;
    }

}
