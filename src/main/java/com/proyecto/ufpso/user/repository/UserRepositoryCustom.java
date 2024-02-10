package com.proyecto.ufpso.user.repository;

import com.proyecto.ufpso.permission.dto.PermissionResponse;

import java.util.List;

public interface UserRepositoryCustom {
    List<PermissionResponse> getPermissionByUserName(String userName);
}
