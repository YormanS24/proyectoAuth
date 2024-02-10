package com.proyecto.ufpso.user.controller;

import com.proyecto.ufpso.user.dto.LoginRequest;
import com.proyecto.ufpso.user.dto.LoginResponse;
import com.proyecto.ufpso.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }
}
