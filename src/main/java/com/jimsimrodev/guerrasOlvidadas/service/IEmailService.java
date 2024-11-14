package com.jimsimrodev.guerrasOlvidadas.service;

public interface IEmailService {
  void enviarCorro(
      String destinatario,
      String asunto,
      String mensaje);
}
