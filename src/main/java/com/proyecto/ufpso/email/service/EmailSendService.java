package com.proyecto.ufpso.email.service;

import com.proyecto.ufpso.user.entity.User;

public interface EmailSendService {
    void emailCodeVerification(String codeVerification,String email);
    void resetPasswordUser(User user,String urlToken);
}
