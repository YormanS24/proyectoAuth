package com.proyecto.ufpso.common.validation.validator;

import com.proyecto.ufpso.common.validation.annotation.PasswordConfirmed;
import com.proyecto.ufpso.user.dto.CreateUserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPasswordConfirmed implements ConstraintValidator<PasswordConfirmed, CreateUserRequest> {

    @Override
    public void initialize(PasswordConfirmed constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreateUserRequest request, ConstraintValidatorContext context) {
        return request.getPassword().equals(request.getConfirmationPassword());
    }
}
