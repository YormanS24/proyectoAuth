package com.proyecto.ufpso.authentication.service.impl;

import com.proyecto.ufpso.authentication.dto.LoginRequest;
import com.proyecto.ufpso.authentication.dto.LoginResponse;
import com.proyecto.ufpso.authentication.service.AuthenticationService;
import com.proyecto.ufpso.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        return new LoginResponse(jwtTokenProvider.generateToken(request.getUserName()));
    }
}
