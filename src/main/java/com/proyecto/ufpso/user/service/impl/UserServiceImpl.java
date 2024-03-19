package com.proyecto.ufpso.user.service.impl;

import com.proyecto.ufpso.user.dto.CreateUserRequest;
import com.proyecto.ufpso.user.entity.User;
import com.proyecto.ufpso.user.repository.UserRepository;
import com.proyecto.ufpso.user.service.PersonSharedService;
import com.proyecto.ufpso.user.service.UserService;
import com.proyecto.ufpso.user.service.UserServiceShared;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserServiceShared userServiceShared;
    private final PersonSharedService personSharedService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserServiceShared userServiceShared, PersonSharedService personSharedService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userServiceShared = userServiceShared;
        this.personSharedService = personSharedService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void createUser(CreateUserRequest request) {
        userServiceShared.validateExistsUserName(request.getUserName());
        User user = User.create(
                request.getUserName().toUpperCase(Locale.ROOT).replace(" ",""),
                passwordEncoder.encode(request.getPassword()),
                request.getImgProfile()
        );
        user = userRepository.save(user);
        personSharedService.createPerson(request.getPerson(),user);
    }
}
