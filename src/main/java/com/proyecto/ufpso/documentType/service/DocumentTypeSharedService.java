package com.proyecto.ufpso.documentType.service;

import com.proyecto.ufpso.documentType.entity.DocumentType;

import java.util.UUID;

public interface DocumentTypeSharedService {
    DocumentType getDocumentType(UUID documentTypeId);
}
