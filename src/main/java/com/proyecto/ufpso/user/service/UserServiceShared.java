package com.proyecto.ufpso.user.service;

import com.proyecto.ufpso.permission.dto.PermissionResponse;
import com.proyecto.ufpso.user.dto.BasicUserInformationResponse;
import com.proyecto.ufpso.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserServiceShared {
    User getUserByUserName(String userName);

    User getUserById(UUID userId);

    List<PermissionResponse> getAllPermission(String userName);

    void saveUser(User user);

    BasicUserInformationResponse getInfoBasicUser(UUID userId);

    User  getUserNameAndEmail(String userName,String email);

    User getUserOfResetPassword(UUID userId,String userName);
}
