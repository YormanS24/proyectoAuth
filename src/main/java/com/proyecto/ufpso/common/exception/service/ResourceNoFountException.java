package com.proyecto.ufpso.common.exception.service;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNoFountException extends RuntimeException{

    private HttpStatus status;

    public ResourceNoFountException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }
}
