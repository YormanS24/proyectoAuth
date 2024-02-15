package com.proyecto.ufpso.authentication.service.impl;

import com.proyecto.ufpso.authentication.dto.LoginRequest;
import com.proyecto.ufpso.authentication.dto.LoginResponse;
import com.proyecto.ufpso.authentication.service.AuthenticationService;
import com.proyecto.ufpso.common.exception.service.AuthenticationFailedException;
import com.proyecto.ufpso.security.jwt.JwtTokenProvider;
import com.proyecto.ufpso.user.entity.User;
import com.proyecto.ufpso.user.service.UserServiceShared;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Locale;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserServiceShared userServiceShared;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserServiceShared userServiceShared, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userServiceShared = userServiceShared;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userServiceShared.getUserByUserName(request.getUserName().toLowerCase(Locale.ROOT).replace(" ",""));

        if (user.isLocked()){
            throw new AuthenticationFailedException("el usuario se encuentra bloqueado");
        }

        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            if (user.getLoginAttempts()>=2){
                this.updateLockedUser(user);
            }else {
                this.updateLoginAttemptsUser(user);
            }
        }

        user.updateLoginAttempts(0);
        user.addCodeVerification(this.generateCodeVerification());

        userServiceShared.saveUser(user);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        return new LoginResponse(jwtTokenProvider.generateToken(request.getUserName()));
    }

    private void updateLockedUser(User user){
        user.updateLocked(true);
        userServiceShared.saveUser(user);
        throw new AuthenticationFailedException("usuario bloqueado debido a intentos fallidos de inicio de sesión.");
    }

    private void updateLoginAttemptsUser(User user){
        user.updateLoginAttempts(user.getLoginAttempts() + 1);
        userServiceShared.saveUser(user);
        throw new AuthenticationFailedException("quedan "+(3-user.getLoginAttempts())+" intentos.");
    }

    private String generateCodeVerification(){
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(999999);
        return String.format("%06d",code);
    }
}
