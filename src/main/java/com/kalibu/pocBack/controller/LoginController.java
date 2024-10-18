package com.kalibu.pocBack.controller;

import com.kalibu.pocBack.config.SecurityConfiguration;
import com.kalibu.pocBack.exception.ApiError;
import com.kalibu.pocBack.model.UserModel;
import com.kalibu.pocBack.repository.UserRepository;
import com.kalibu.pocBack.vo.UserResponseVO;
import com.kalibu.pocBack.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/login")
@CrossOrigin(exposedHeaders = "Acess-Control-Allow-Origin")
@Tag(name = "login", description = "Rest API for login")
@Log
public class LoginController {

    @Autowired
    private UserRepository userCredentialRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Operation(
            operationId = "/sign-in",
            summary = "Sign In the user",
            description = "Sign In the user",
            tags = {"login"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponseVO.class)))
                    }),
                    @ApiResponse(responseCode = "404", description = "error", content = {
                            @Content(mediaType = "application/json")
                    })
            }
    )
    @PostMapping("/sign-in")
    public ResponseEntity<UserResponseVO> signIn(@RequestBody UserVO vo) {
        log.info("/signIn, vo=" + vo);

        final UserModel user = userCredentialRepository
                .findByEmailAndPassword(
                        vo.email(),
                        securityConfiguration.passwordEncoder().encode(vo.password()))
                .orElseThrow(EntityNotFoundException::new);

        final UserResponseVO response = new UserResponseVO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());

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