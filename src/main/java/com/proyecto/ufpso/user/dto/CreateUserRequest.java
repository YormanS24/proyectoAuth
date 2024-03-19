package com.proyecto.ufpso.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.ufpso.common.validation.annotation.PasswordConfirmed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@PasswordConfirmed
public class CreateUserRequest {
    @NotBlank
    @Size(min = 6,max = 12)
    @JsonProperty(value = "user_name",required = true)
    private String userName;

    @JsonProperty(value = "img_profile")
    private String imgProfile;

    @NotBlank
    @Size(max = 30)
    @JsonProperty(value = "password",required = true)
    private String password;

    @NotBlank
    @JsonProperty(value = "confirmation_password",required = true)
    private String confirmationPassword;

    @Valid
    @JsonProperty(value = "person",required = true)
    private CreatePersonRequest person;
}
