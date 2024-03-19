package com.proyecto.ufpso.documentType.service.impl;

import com.proyecto.ufpso.common.exception.service.ResourceNotFoundException;
import com.proyecto.ufpso.documentType.entity.DocumentType;
import com.proyecto.ufpso.documentType.repository.DocumentTypeRepository;
import com.proyecto.ufpso.documentType.service.DocumentTypeSharedService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DocumentTypeSharedServiceImpl implements DocumentTypeSharedService {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeSharedServiceImpl(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public DocumentType getDocumentType(UUID documentTypeId) {
        return documentTypeRepository.findById(documentTypeId).orElseThrow(()-> new ResourceNotFoundException("tipo de documento no disponible"));
    }
}
