package com.kalibu.pocBack.controller;

import com.kalibu.pocBack.service.UserService;
import com.kalibu.pocBack.vo.RecoveryJwtTokenDto;
import com.kalibu.pocBack.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
@CrossOrigin(exposedHeaders = "Acess-Control-Allow-Origin")
public class SecurityController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody UserVO loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

}
