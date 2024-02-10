package com.proyecto.ufpso.permission.entity;

import com.proyecto.ufpso.module.entity.Module;
import com.proyecto.ufpso.role.entity.Role;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID2")
    @Column(name = "permission_id")
    private UUID permissionId;

    @Column(name = "title",nullable = false,length = 100)
    private String title;

    @Column(name = "permission_name",nullable = false,unique = true)
    private String permissionName;

    @Transient
    private boolean checked;

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "permissions",cascade = CascadeType.REFRESH)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id",nullable = false)
    private Module module;
}
