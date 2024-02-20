package com.proyecto.ufpso.module.service.impl;

import com.proyecto.ufpso.module.dto.UserModulesResponse;
import com.proyecto.ufpso.module.repository.ModuleCustomRepository;
import com.proyecto.ufpso.module.service.ModuleServiceShard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModuleServiceSharedImpl implements ModuleServiceShard {

    private final ModuleCustomRepository moduleCustomRepository;

    public ModuleServiceSharedImpl(ModuleCustomRepository moduleCustomRepository) {
        this.moduleCustomRepository = moduleCustomRepository;
    }

    @Override
    public List<UserModulesResponse> getModuleOfUser(UUID userId) {
        return moduleCustomRepository.getModuleOfUser(userId);
    }
}
