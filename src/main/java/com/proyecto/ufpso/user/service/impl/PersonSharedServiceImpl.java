package com.proyecto.ufpso.user.service.impl;

import com.proyecto.ufpso.documentType.service.DocumentTypeSharedService;
import com.proyecto.ufpso.user.dto.CreatePersonRequest;
import com.proyecto.ufpso.user.entity.Person;
import com.proyecto.ufpso.user.entity.User;
import com.proyecto.ufpso.user.repository.PersonRepository;
import com.proyecto.ufpso.user.service.PersonSharedService;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class PersonSharedServiceImpl implements PersonSharedService {

    private final PersonRepository personRepository;
    private final DocumentTypeSharedService documentTypeSharedService;

    public PersonSharedServiceImpl(PersonRepository personRepository, DocumentTypeSharedService documentTypeSharedService) {
        this.personRepository = personRepository;
        this.documentTypeSharedService = documentTypeSharedService;
    }

    @Override
    public void createPerson(CreatePersonRequest request, User user) {
        Person person = Person.create(
                request.getPersonName().toUpperCase(Locale.ROOT).trim(),
                request.getLastname().toUpperCase(Locale.ROOT).trim(),
                request.getDocumentNumber().replace(" ",""),
                request.getPhone().trim().replace(" ",""),
                request.getEmail().toUpperCase(Locale.ROOT),
                request.getDirection().toUpperCase(Locale.ROOT).replace(" ",""),
                request.getBirthdate()
        );
        person.addUser(user);
        person.addDocumentType(documentTypeSharedService.getDocumentType(request.getDocumentType()));
        personRepository.save(person);
    }
}
