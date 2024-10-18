package com.kalibu.pocBack;

import com.kalibu.pocBack.model.FooModel;
import com.kalibu.pocBack.model.UserCredentialModel;
import com.kalibu.pocBack.repository.FooRepository;
import com.kalibu.pocBack.repository.UserCredentialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    private final FooRepository fooRepository;

    private final UserCredentialRepository userCredentialRepository;

    public DataLoader(FooRepository fooRepository, UserCredentialRepository userCredentialRepository) {
        this.fooRepository = fooRepository;
        this.userCredentialRepository = userCredentialRepository;
    }

    @Override
    public void run(String... args) {

        //create user for test
        UserCredentialModel userCredentialModel = new UserCredentialModel();
        userCredentialModel.setEmail("test@kalibu.com");
        userCredentialModel.setPassword("test");
        userCredentialModel.setFirstName("Test");
        userCredentialModel.setLastName("Kalibu");
        userCredentialRepository.save(userCredentialModel);

        //create foos
        for (int i = 0; i < 10; i++) {
            fooRepository.save(new FooModel(UUID.randomUUID().toString()));
        }

    }
}
