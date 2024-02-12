package com.proyecto.ufpso.common.exception.service;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AccessDeniedException extends RuntimeException{

    private HttpStatus status;

    public AccessDeniedException(String message) {
        super(message);
        this.status = HttpStatus.FORBIDDEN;
    }
}
