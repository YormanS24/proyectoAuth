package com.proyecto.ufpso.user.service;

import com.proyecto.ufpso.user.dto.CreatePersonRequest;
import com.proyecto.ufpso.user.entity.User;

public interface PersonSharedService {
    void createPerson(CreatePersonRequest request, User user);
}
