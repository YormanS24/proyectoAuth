package com.proyecto.ufpso.documentType.service.impl;

import com.proyecto.ufpso.documentType.dto.DocumentTypeResponse;
import com.proyecto.ufpso.documentType.repository.DocumentTypeRepository;
import com.proyecto.ufpso.documentType.service.DocumentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }


    @Override
    public List<DocumentTypeResponse> getAllDocumentType() {
        return documentTypeRepository.getAllDocumentType();
    }
}
