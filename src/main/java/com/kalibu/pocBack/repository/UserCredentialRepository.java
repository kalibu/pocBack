package com.kalibu.pocBack.repository;

import com.kalibu.pocBack.model.UserCredentialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredentialModel, Long> {

    Optional<UserCredentialModel> findByEmailAndPassword(final String email, final String password);

}
