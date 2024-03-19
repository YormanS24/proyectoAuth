package com.proyecto.ufpso.user.controller;

import com.proyecto.ufpso.user.dto.CreateUserRequest;
import com.proyecto.ufpso.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping("/")
    @Operation(description = "CREATE USER CUSTOMER")
    @ApiResponses({@ApiResponse(responseCode = "201",description = "CREATED"),@ApiResponse(responseCode = "400",description = "BAD REQUEST")})
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody CreateUserRequest request){
        userService.createUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
