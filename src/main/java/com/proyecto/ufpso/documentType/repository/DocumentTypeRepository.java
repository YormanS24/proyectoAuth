package com.proyecto.ufpso.documentType.repository;

import com.proyecto.ufpso.documentType.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, UUID>,DocumentTypeRepositoryCustom {
}
