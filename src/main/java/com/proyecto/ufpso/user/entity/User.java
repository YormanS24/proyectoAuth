package com.proyecto.ufpso.user.entity;

import com.proyecto.ufpso.role.entity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID4")
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "user_name",nullable = false,unique = true,length = 50)
    private String userName;

    @Column(name = "password", nullable = false,length = 100)
    private String password;

    @Column(name = "locked",nullable = false)
    private boolean locked;

    @Column(name = "login_attempts")
    private int loginAttempts;

    @Column(name = "administrator",nullable = false)
    private boolean administrator;

    @Column(name = "code_verification",length = 7)
    private String codeVerification;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "user_role",schema = "main",
            joinColumns = @JoinColumn(name = "user_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id",nullable = false),
            uniqueConstraints = @UniqueConstraint(name = "uc_user_id_role_id",columnNames = {"user_id","role_id"})
    )
    Set<Role> roles;

}
