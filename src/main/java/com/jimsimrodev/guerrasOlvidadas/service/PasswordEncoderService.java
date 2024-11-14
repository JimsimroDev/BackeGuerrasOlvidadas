package com.jimsimrodev.guerrasOlvidadas.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public String encodePassword(String contrasena) {
    return encoder.encode(contrasena);
  }

  public Boolean mactches(String contrasenaNormal, String contrasenaProcesada) {
    return encoder.matches(contrasenaNormal, contrasenaProcesada);
  }
}
