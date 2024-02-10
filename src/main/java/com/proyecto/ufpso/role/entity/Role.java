package com.proyecto.ufpso.role.entity;

import com.proyecto.ufpso.permission.entity.Permission;
import com.proyecto.ufpso.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID3")
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "role_name",nullable = false,unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles",cascade = CascadeType.REFRESH)
    Set<User> users;

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "role_permission",schema = "main",
            joinColumns = @JoinColumn(name = "role_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "permission_id",nullable = false),
            uniqueConstraints = @UniqueConstraint(name = "uc_role_id_permission_id",columnNames = {"role_id","permission_id"})
    )
    private Set<Permission> permissions;
}
