package com.jimsimrodev.guerrasOlvidadas.domain.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistroDatosEmail(
    @NotBlank @Email String destinatario) {
}
