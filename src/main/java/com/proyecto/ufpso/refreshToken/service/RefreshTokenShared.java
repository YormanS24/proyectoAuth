package com.proyecto.ufpso.refreshToken.service;

import com.proyecto.ufpso.refreshToken.entity.RefreshToken;
import com.proyecto.ufpso.user.entity.User;

import java.util.UUID;

public interface RefreshTokenShared {
    UUID createRefreshToken(User user);
    void deleteRefreshTokenByUser(User user);
    RefreshToken evaluateRefreshToken(UUID refreshToken);
}
