package com.proyecto.ufpso.module.repository;

import com.proyecto.ufpso.module.dto.UserModulesResponse;

import java.util.List;
import java.util.UUID;

public interface ModuleCustomRepository {

    List<UserModulesResponse> getModuleOfUser(UUID userId);
}
