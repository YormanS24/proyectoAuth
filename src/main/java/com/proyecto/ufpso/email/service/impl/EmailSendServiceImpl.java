package com.proyecto.ufpso.email.service.impl;

import com.proyecto.ufpso.email.dto.EmailRequest;
import com.proyecto.ufpso.email.service.EmailSendService;
import com.proyecto.ufpso.email.service.EmailService;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailSendServiceImpl implements EmailSendService {

    private final EmailService emailService;

    public EmailSendServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void emailCodeVerification(String codeVerification) {
        emailService.sendEmail(EmailRequest.create(
                "sanchezyorman0@gmail.com",
                "Codigo de Verificacion",
                "Su coigo de Verificacion del CINE LEONELDA: "+codeVerification,
                null,
                null
        ));
    }
}
