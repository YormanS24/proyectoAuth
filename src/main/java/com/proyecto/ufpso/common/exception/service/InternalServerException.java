package com.proyecto.ufpso.common.exception.service;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InternalServerException extends RuntimeException{
    private HttpStatus status;

    public InternalServerException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
