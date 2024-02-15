package com.proyecto.ufpso.user.service;

import com.proyecto.ufpso.permission.dto.PermissionResponse;
import com.proyecto.ufpso.user.entity.User;

import java.util.List;

public interface UserServiceShared {
    User getUserByUserName(String userName);

    List<PermissionResponse> getAllPermission(String userName);

    void saveUser(User user);
}
