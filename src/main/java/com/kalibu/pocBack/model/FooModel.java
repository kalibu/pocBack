package com.kalibu.pocBack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fooModel")
@Data
@NoArgsConstructor
public class FooModel {

    public FooModel(final String p){
        this.fooProperty = p;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fooProperty")
    @NotNull
    @NotEmpty
    private String fooProperty;

}