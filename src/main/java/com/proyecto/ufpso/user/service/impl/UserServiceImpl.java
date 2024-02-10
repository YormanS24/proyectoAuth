package com.proyecto.ufpso.user.service.impl;

import com.proyecto.ufpso.security.jwt.JwtTokenProvider;
import com.proyecto.ufpso.user.dto.LoginRequest;
import com.proyecto.ufpso.user.dto.LoginResponse;
import com.proyecto.ufpso.user.repository.UserRepository;
import com.proyecto.ufpso.user.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
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
