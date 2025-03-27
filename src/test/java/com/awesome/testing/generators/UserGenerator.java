package com.awesome.testing.generators;

import com.awesome.testing.http.dto.RegisterRequestDto;
import com.awesome.testing.http.dto.Roles;
import lombok.experimental.UtilityClass;
import net.datafaker.Faker;

import java.util.List;

@UtilityClass
public class UserGenerator {

    private static final Faker FAKER = new Faker();

    public static RegisterRequestDto getRandomUser() {
        return RegisterRequestDto.builder()
                .username(FAKER.internet().username())
                .password(FAKER.internet().password())
                .roles(List.of(Roles.ROLE_ADMIN, Roles.ROLE_CLIENT))
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .email(FAKER.internet().emailAddress())
                .build();
    }

}
