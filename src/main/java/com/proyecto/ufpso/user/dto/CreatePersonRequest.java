package com.proyecto.ufpso.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class CreatePersonRequest {

    @NotBlank
    @Size(min = 5,max = 20)
    @JsonProperty(value = "person_name",required = true)
    private String personName;

    @NotBlank
    @Size(min = 5,max = 20)
    @JsonProperty(value = "lastname",required = true)
    private String lastname;

    @NotBlank
    @Email
    @JsonProperty(value = "email",required = true)
    private String email;

    @JsonProperty(value = "direction")
    private String direction;

    @NotNull
    @JsonProperty(value = "document_type",required = true)
    private UUID documentType;

    @Size(min = 10,max = 15)
    @JsonProperty(value = "document_number",required = true)
    private String documentNumber;

    @NotBlank
    @Size(min = 10,max = 15)
    @JsonProperty(value = "phone",required = true)
    private String phone;

    @NotNull
    @JsonProperty(value = "birthdate",required = true)
    private LocalDate birthdate;
}
