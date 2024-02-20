package com.proyecto.ufpso.common.encryption;


public interface Encryption {
    String encrypt(String stringToEncrypt, String key);

    String decrypt(String stringToDecrypt, String key);
}
