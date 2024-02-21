package com.proyecto.ufpso.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateUserRequest {
    @NotBlank
    @JsonProperty(value = "user_name",required = true)
    private String userName;

    @JsonProperty(value = "img_profile")
    private String imgProfile;

    @NotBlank
    @JsonProperty(value = "password",required = true)
    private String password;

    @NotBlank
    @JsonProperty(value = "confirmation_password",required = true)
    private String confirmationPassword;
}
