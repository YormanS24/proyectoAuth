package com.proyecto.ufpso.common.exception.controller;

import com.proyecto.ufpso.common.exception.dto.ErrorExceptionResponse;
import com.proyecto.ufpso.common.exception.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorExceptionResponse> internalServerException(InternalServerException in){
        return new ResponseEntity<>(ErrorExceptionResponse.create(in.getMessage(),in.getStatus()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorExceptionResponse> MethodArgumentException(MethodArgumentNotValidException me){
        return new ResponseEntity<>(ErrorExceptionResponse.create(me.getMessage(),me.getStatus()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNoFountException.class)
    public ResponseEntity<ErrorExceptionResponse> resourceNotFoundException(ResourceNoFountException re){
        return new ResponseEntity<>(ErrorExceptionResponse.create(re.getMessage(),re.getStatus()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorExceptionResponse> authException(AuthenticationFailedException au){
        return new ResponseEntity<>(ErrorExceptionResponse.create(au.getMessage(),au.getStatus()),HttpStatus.UNAUTHORIZED);
    }
}