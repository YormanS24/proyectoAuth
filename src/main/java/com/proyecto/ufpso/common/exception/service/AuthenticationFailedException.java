package com.proyecto.ufpso.common.exception.service;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationFailedException extends RuntimeException{

    private HttpStatus status;

    public AuthenticationFailedException(String message) {
        super(message);
        this.status = HttpStatus.UNAUTHORIZED;
    }
}
