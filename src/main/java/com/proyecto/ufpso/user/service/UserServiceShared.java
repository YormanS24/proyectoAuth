package com.proyecto.ufpso.user.service;

import com.proyecto.ufpso.permission.dto.PermissionResponse;
import com.proyecto.ufpso.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceShared {
    Optional<User> getUserByUserName(String userName);

    List<PermissionResponse> getAllPermission(String userName);
}
