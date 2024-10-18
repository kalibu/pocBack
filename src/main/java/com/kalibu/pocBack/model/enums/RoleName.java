package com.kalibu.pocBack.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleName {
    ROLE_ADMINISTRATOR("ADMINISTRATOR"),
    ROLE_FOO("FOO");

    private String text;
}
