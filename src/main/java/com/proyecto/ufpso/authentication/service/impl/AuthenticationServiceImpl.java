package com.proyecto.ufpso.authentication.service.impl;

import com.proyecto.ufpso.authentication.dto.AuthenticationMfaRequest;
import com.proyecto.ufpso.authentication.dto.LoginRequest;
import com.proyecto.ufpso.authentication.dto.LoginResponse;
import com.proyecto.ufpso.authentication.service.AuthenticationService;
import com.proyecto.ufpso.common.exception.service.AuthenticationFailedException;
import com.proyecto.ufpso.email.service.EmailSendService;
import com.proyecto.ufpso.module.service.ModuleServiceShard;
import com.proyecto.ufpso.security.jwt.JwtTokenProvider;
import com.proyecto.ufpso.user.entity.User;
import com.proyecto.ufpso.user.service.UserServiceShared;
import com.proyecto.ufpso.user.util.GenericLoginAttempts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserServiceShared userServiceShared;
    private final PasswordEncoder passwordEncoder;
    private final EmailSendService emailSendService;
    private final ModuleServiceShard moduleServiceShard;
    @Value("${settings.auth.time-resent-email}")
    private int timeResentEmail;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserServiceShared userServiceShared, PasswordEncoder passwordEncoder, EmailSendService emailSendService, ModuleServiceShard moduleServiceShard) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userServiceShared = userServiceShared;
        this.passwordEncoder = passwordEncoder;
        this.emailSendService = emailSendService;
        this.moduleServiceShard = moduleServiceShard;
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
                this.updateLoginAttemptsUser(user,GenericLoginAttempts.LOGIN_ATTEMPTS);
            }
        }

        user.updateLoginAttempts(0);
        user.addCodeVerification(this.generateCodeVerification(user));
        user.updateQuantityResentEmail(0);
        userServiceShared.saveUser(user);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        return new LoginResponse(user.getUserId());
    }

    @Override
    public LoginResponse authenticationMfa(AuthenticationMfaRequest request) {
        User user = userServiceShared.getUserById(request.getUserId());

        if (user.isLocked()){
            throw new AuthenticationFailedException("el usuario se encuentra bloqueado");
        }

        if (user.getCreateCodeVerification() == null || !Objects.equals(user.getCodeVerification(), request.getCode())){
            this.updateLoginAttemptsUser(user,GenericLoginAttempts.LOGIN_ATTEMPTS_MFA);
        }

        Duration duration = Duration.between(user.getCreateCodeVerification(), LocalDateTime.now());
        if (duration.toMinutes() > 5){
            throw new AuthenticationFailedException("el código ha expirado, vuelve a iniciar sesión");
        }

        user.updateLoginAttemptsMfa(0);
        user.resetCodeVerification();
        user.updateQuantityResentEmail(0);
        userServiceShared.saveUser(user);
        return new LoginResponse(jwtTokenProvider.generateToken(user.getUserName()), UUID.randomUUID(),userServiceShared.getInfoBasicUser(request.getUserId()),moduleServiceShard.getModuleOfUser(request.getUserId()),userServiceShared.getAllPermission(user.getUserName()));
    }

    @Override
    public void resentEmailByUser(UUID userId) {
        User user = userServiceShared.getUserById(userId);
        user.addCodeVerification(this.generateCodeVerification(user));
        user.updateQuantityResentEmail(user.getQuantityResentEmail()+1);
        userServiceShared.saveUser(user);
    }

    private void updateLockedUser(User user){
        user.updateLocked(true);
        userServiceShared.saveUser(user);
        throw new AuthenticationFailedException("usuario bloqueado debido a intentos fallidos de inicio de sesión.");
    }

    private void updateLoginAttemptsUser(User user, GenericLoginAttempts attempts){
        if (GenericLoginAttempts.LOGIN_ATTEMPTS.equals(attempts)){
            user.updateLoginAttempts(user.getLoginAttempts() + 1);
        }else {
            if (user.getLoginAttemptsMfa()>=2){
                this.updateLockedUser(user);
            }
            user.updateLoginAttemptsMfa(user.getLoginAttemptsMfa()+1);
        }
        userServiceShared.saveUser(user);
        String message = (attempts.equals(GenericLoginAttempts.LOGIN_ATTEMPTS))?".":"el código es inválido";
        throw new AuthenticationFailedException("quedan "+(attempts.equals(GenericLoginAttempts.LOGIN_ATTEMPTS)?3-user.getLoginAttempts():3-user.getLoginAttemptsMfa())+" intentos "+message);
    }

    private String generateCodeVerification(User user){
        Duration duration = Duration.between(user.getCreateCodeVerification(),LocalDateTime.now());
        if (user.getQuantityResentEmail()>=3 && duration.toMinutes() <= timeResentEmail){
            throw new AuthenticationFailedException("has alcanzado el límite de intentos para reenviar el código a tu correo, reintenta en "+(timeResentEmail-duration.toMinutes())+" minutos.");
        }
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(999999);
        String codeVerification = String.format("%06d",code);
        emailSendService.emailCodeVerification(codeVerification);
        return codeVerification;
    }
}
