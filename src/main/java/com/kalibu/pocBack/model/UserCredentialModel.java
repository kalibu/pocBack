package com.kalibu.pocBack.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userCredentialModel")
@Data
@NoArgsConstructor
public class UserCredentialModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    private String password;

    private String firstName;
    private String lastName;
}
