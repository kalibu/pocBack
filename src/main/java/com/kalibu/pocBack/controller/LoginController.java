package com.kalibu.pocBack.controller;

import com.kalibu.pocBack.exception.ApiError;
import com.kalibu.pocBack.model.FooModel;
import com.kalibu.pocBack.model.UserCredentialModel;
import com.kalibu.pocBack.repository.FooRepository;
import com.kalibu.pocBack.repository.UserCredentialRepository;
import com.kalibu.pocBack.vo.UserCredentialResponseVO;
import com.kalibu.pocBack.vo.UserCredentialVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/login")
@CrossOrigin(exposedHeaders = "Acess-Control-Allow-Origin")
@Tag(name = "login", description = "Rest API for login")
@Log
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Operation(
            operationId = "/sign-in",
            summary = "Sign In the user",
            description = "Sign In the user",
            tags = {"login"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserCredentialResponseVO.class)))
                    }),
                    @ApiResponse(responseCode = "404", description = "error", content = {
                            @Content(mediaType = "application/json")
                    })
            }
    )
    @PostMapping("/sign-in")
    public ResponseEntity<UserCredentialResponseVO> signIn(@RequestBody UserCredentialVO vo) {
        log.info("/signIn, vo={}", vo);

        final UserCredentialModel user = userCredentialRepository
                .findByEmailAndPassword(vo.getEmail(), vo.getPassword())
                .orElseThrow(EntityNotFoundException::new);

        final UserCredentialResponseVO response = new UserCredentialResponseVO();
        response.setUserId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleIllegalArgument() {
        ApiError apiError = new ApiError("Invalid user credentials.");
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(apiError);
    }

}