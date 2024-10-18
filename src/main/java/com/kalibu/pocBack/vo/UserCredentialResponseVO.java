package com.kalibu.pocBack.vo;

import lombok.Data;

@Data
public class UserCredentialResponseVO {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
}
