package com.proyecto.ufpso.user.service;

import com.proyecto.ufpso.user.dto.LoginRequest;
import com.proyecto.ufpso.user.dto.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest request);
}
