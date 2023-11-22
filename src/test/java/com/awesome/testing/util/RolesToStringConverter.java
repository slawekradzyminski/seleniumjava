package com.awesome.testing.util;

import com.awesome.testing.generators.Roles;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RolesToStringConverter {

    public static String convert(Roles[] rolesArray) {
        if (rolesArray == null || rolesArray.length == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < rolesArray.length; i++) {
            stringBuilder.append(rolesArray[i].name());
            if (i < rolesArray.length - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
