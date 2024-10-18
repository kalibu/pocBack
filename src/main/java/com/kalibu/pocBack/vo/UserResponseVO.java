package com.kalibu.pocBack.vo;

import lombok.Data;

public record UserResponseVO(
        Integer userId,
        String firstName,
        String lastName,
        String email
) {
}
