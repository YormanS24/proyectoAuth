package com.proyecto.ufpso.refreshToken.repository;

import com.proyecto.ufpso.refreshToken.entity.RefreshToken;
import com.proyecto.ufpso.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(UUID token);
    RefreshToken findByUser(User user);
}
