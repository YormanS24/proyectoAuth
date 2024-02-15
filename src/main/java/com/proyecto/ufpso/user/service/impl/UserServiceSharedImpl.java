package com.proyecto.ufpso.user.service.impl;

import com.proyecto.ufpso.common.exception.service.AuthenticationFailedException;
import com.proyecto.ufpso.permission.dto.PermissionResponse;
import com.proyecto.ufpso.user.entity.User;
import com.proyecto.ufpso.user.repository.UserRepository;
import com.proyecto.ufpso.user.service.UserServiceShared;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceSharedImpl implements UserServiceShared {

    private final UserRepository userRepository;

    public UserServiceSharedImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(()-> new AuthenticationFailedException("credenciales incorrectas"));
    }

    @Override
    public List<PermissionResponse> getAllPermission(String userName) {
        return userRepository.getPermissionByUserName(userName);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
