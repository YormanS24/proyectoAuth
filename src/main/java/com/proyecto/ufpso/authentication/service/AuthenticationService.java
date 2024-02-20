package com.proyecto.ufpso.authentication.service;

import com.proyecto.ufpso.authentication.dto.*;
import com.proyecto.ufpso.refreshToken.dto.RefreshTokenRequest;
import com.proyecto.ufpso.refreshToken.dto.RefreshTokenResponse;

import java.util.UUID;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
    LoginResponse authenticationMfa(AuthenticationMfaRequest request);
    void resentEmailByUser(UUID userId);
    void forgotPassword(ResetPasswordRequest request);
    void verifyTokenResetPassword(TokenResentPasswordRequest request);
    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
}
