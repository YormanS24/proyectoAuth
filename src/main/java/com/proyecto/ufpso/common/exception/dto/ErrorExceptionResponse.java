package com.proyecto.ufpso.common.exception.dto;

import org.springframework.http.HttpStatus;

public class ErrorExceptionResponse {

    private String message;

    private HttpStatus status;

    public ErrorExceptionResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public static ErrorExceptionResponse create(String message, HttpStatus status){
        return new ErrorExceptionResponse(message,status);
    }
}
