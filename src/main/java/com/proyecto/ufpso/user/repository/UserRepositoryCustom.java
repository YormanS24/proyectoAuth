package com.proyecto.ufpso.user.repository;

import com.proyecto.ufpso.permission.dto.PermissionResponse;
import com.proyecto.ufpso.user.dto.BasicUserInformationResponse;

import java.util.List;
import java.util.UUID;

public interface UserRepositoryCustom {
    List<PermissionResponse> getPermissionByUserName(String userName);

    BasicUserInformationResponse getInfoBasicUser(UUID userId);
}
