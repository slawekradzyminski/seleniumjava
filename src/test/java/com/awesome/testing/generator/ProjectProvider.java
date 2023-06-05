package com.awesome.testing.generator;

import com.awesome.testing.generator.dto.Project;
import lombok.experimental.UtilityClass;
import net.datafaker.Faker;

@UtilityClass
public class ProjectProvider {

    private static final Faker FAKER = new Faker();

    public static Project getRandomProject() {
        return Project.builder()
                .description(FAKER.lorem().sentence())
                .prefix(FAKER.lorem().characters(4))
                .name(FAKER.lorem().characters(8))
                .build();
    }

}
