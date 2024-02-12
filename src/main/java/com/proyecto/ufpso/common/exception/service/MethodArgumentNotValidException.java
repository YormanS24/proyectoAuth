package com.proyecto.ufpso.common.exception.service;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MethodArgumentNotValidException extends RuntimeException{

    private HttpStatus status;

    public MethodArgumentNotValidException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
