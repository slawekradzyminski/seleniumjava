package com.awesome.testing.generator;

import com.awesome.testing.generator.dto.User;
import lombok.experimental.UtilityClass;
import net.datafaker.Faker;

@UtilityClass
public class UserProvider {

    private static final Faker FAKER = new Faker();

    public static User getRandomUser() {
        return User.builder()
                .username(FAKER.name().username())
                .email(FAKER.internet().emailAddress())
                .password(FAKER.internet().password())
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .build();
    }

}
