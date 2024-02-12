package com.proyecto.ufpso.authentication.service;

import com.proyecto.ufpso.authentication.dto.LoginRequest;
import com.proyecto.ufpso.authentication.dto.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);
}
