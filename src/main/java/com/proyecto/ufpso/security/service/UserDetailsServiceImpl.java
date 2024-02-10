package com.proyecto.ufpso.security.service;

import com.proyecto.ufpso.permission.dto.PermissionResponse;
import com.proyecto.ufpso.user.entity.User;
import com.proyecto.ufpso.user.service.UserServiceShared;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceShared userServiceShared;

    public UserDetailsServiceImpl(UserServiceShared userServiceShared) {
        this.userServiceShared = userServiceShared;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceShared.getUserByUserName(username).orElseThrow(()-> new UsernameNotFoundException("El usuario solicitado no se encuentra registrado."));
        List<PermissionResponse> permissionResponses = userServiceShared.getAllPermission(username);
        return CustomUserDetailServiceImpl.build(user,permissionResponses);
    }
}
