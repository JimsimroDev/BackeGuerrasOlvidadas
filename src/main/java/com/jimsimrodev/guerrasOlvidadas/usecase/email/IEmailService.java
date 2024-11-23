package com.jimsimrodev.guerrasOlvidadas.usecase.email;

public interface IEmailService {
    void recordarUsuario(
            String destinatario,
            String asunto,
            String nombre,
            String usuario);

    void enviarLink(
            String destinatario,
            String asunto,
            String nombre
    );
}
