package com.proyecto.ufpso.user.repository;

import com.proyecto.ufpso.user.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
