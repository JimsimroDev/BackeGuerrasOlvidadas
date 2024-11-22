package com.jimsimrodev.guerrasOlvidadas.usecase.email;

public interface IEmailService {
  void enviarCorro(
      String destinatario,
      String asunto,
      String mensaje);
}
