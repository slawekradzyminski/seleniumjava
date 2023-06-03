package com.awesome.testing.generator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProvider {

    private static final Faker FAKER = new Faker();

    public static User getRandomUser() {
        return User.builder()
                .username(FAKER.name().username())
                .password(FAKER.internet().password())
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .email(FAKER.internet().emailAddress())
                .build();
    }

}
