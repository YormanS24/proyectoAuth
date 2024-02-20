package com.proyecto.ufpso.module.service;

import com.proyecto.ufpso.module.dto.UserModulesResponse;

import java.util.List;
import java.util.UUID;

public interface ModuleServiceShard {

    List<UserModulesResponse> getModuleOfUser(UUID userId);
}
