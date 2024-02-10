package com.proyecto.ufpso.module.entity;

import com.proyecto.ufpso.permission.entity.Permission;
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
@Table(name = "module")
public class Module {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID1")
    @Column(name = "module_id")
    private UUID moduleId;

    @Column(name = "module_name",unique = true,nullable = false,length = 20)
    private String moduleName;

    @Column(name = "ico",unique = true,nullable = false)
    private String ico;

    @Column(name = "description",nullable = false,length = 200)
    private String description;

    @Column(name = "orden",nullable = false,unique = true)
    private int order;

    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "module")
    private Set<Permission> permissions;
}
