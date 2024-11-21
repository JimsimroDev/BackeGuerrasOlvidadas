package com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarDatosContrasena(
        @NotNull Long id,
        @NotBlank String contrasena) {
}
