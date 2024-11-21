package com.jimsimrodev.guerrasOlvidadas.infra.repository;

public interface IEmailService {
    void enviarCorro(
            String destinatario,
            String asunto,
            String mensaje);
}
