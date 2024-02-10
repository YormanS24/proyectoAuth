package com.proyecto.ufpso.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    @JsonProperty(value = "token_jwt",required = true)
    private String jwt;

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }
}
