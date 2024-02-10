package com.proyecto.ufpso.role.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RoleRequest {

    @NotBlank
    @JsonProperty(value = "role_name",required = true)
    private String roleName;
}
