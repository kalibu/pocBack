package com.kalibu.pocBack;

import com.kalibu.pocBack.config.SecurityConfiguration;
import com.kalibu.pocBack.model.FooModel;
import com.kalibu.pocBack.model.RoleModel;
import com.kalibu.pocBack.model.UserModel;
import com.kalibu.pocBack.model.enums.RoleName;
import com.kalibu.pocBack.repository.FooRepository;
import com.kalibu.pocBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FooRepository fooRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Override
    public void run(String... args) {

        //create user for test
        UserModel userModel =
        UserModel
                .builder()
                .email("test@kalibu.com")
                .password(securityConfiguration.passwordEncoder().encode("test"))
                .firstName("Test")
                .lastName("Kalibu")
                .roles(
                        List.of(
                                RoleModel.builder().name(RoleName.ROLE_ADMINISTRATOR).build(),
                                RoleModel.builder().name(RoleName.ROLE_FOO).build()
                        ))
                .build();
        userRepository.save(userModel);

        //create foos
        for (int i = 0; i < 10; i++) {
            fooRepository.save(new FooModel(UUID.randomUUID().toString()));
        }

    }
}
