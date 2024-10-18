package com.kalibu.pocBack.repository;

import com.kalibu.pocBack.model.FooModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepository extends JpaRepository<FooModel, Long> {

}
