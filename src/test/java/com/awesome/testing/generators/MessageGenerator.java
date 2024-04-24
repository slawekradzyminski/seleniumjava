package com.awesome.testing.generators;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageGenerator {

    private static final Faker FAKER = new Faker();

    public static String getRandomMessage() {
        return FAKER.lorem().sentence();
    }

}
