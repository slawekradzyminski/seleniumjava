package com.awesome.testing.generator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {

    String prefix;
    String name;
    String description;

}
