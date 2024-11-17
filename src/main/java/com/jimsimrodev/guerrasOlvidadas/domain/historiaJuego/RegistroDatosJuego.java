package com.jimsimrodev.guerrasOlvidadas.domain.historiaJuego;

import com.jimsimrodev.guerrasOlvidadas.domain.persona.Persona;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroDatosJuego(
        @NotBlank String escenario,
        @NotBlank String premios,
        @NotNull Long idUsuario,
        Persona usuario
) {
}
