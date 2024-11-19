package com.jimsimrodev.guerrasOlvidadas.domain.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizarDatosContrasena(
    @NotNull Long id,
    @NotBlank String contrasena) {
}
