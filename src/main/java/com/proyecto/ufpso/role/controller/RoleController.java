package com.proyecto.ufpso.role.controller;

import com.proyecto.ufpso.role.dto.RoleRequest;
import com.proyecto.ufpso.role.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    @PreAuthorize("hasPermission('HttpStatus','CREATE_ROLE')")
    public ResponseEntity<HttpStatus> createRole(@Valid @RequestBody RoleRequest request){
        roleService.createRole(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
