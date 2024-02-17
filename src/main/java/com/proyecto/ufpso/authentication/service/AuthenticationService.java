package com.proyecto.ufpso.authentication.service;

import com.proyecto.ufpso.authentication.dto.AuthenticationMfaRequest;
import com.proyecto.ufpso.authentication.dto.LoginRequest;
import com.proyecto.ufpso.authentication.dto.LoginResponse;

import java.util.UUID;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
    LoginResponse authenticationMfa(AuthenticationMfaRequest request);
    void resentEmailByUser(UUID userId);
}
