package com.proyecto.ufpso.email.service;

import com.proyecto.ufpso.email.dto.EmailRequest;

public interface EmailService {
    void sendEmail(EmailRequest request);
}
