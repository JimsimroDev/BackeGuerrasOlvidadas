package com.jimsimrodev.guerrasOlvidadas.adapter.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistroDatosEmail(
        @NotBlank @Email String destinatario) {
}
