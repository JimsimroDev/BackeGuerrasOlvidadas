package com.jimsimrodev.guerrasOlvidadas.adapter.dto.historiaJuego;

import com.jimsimrodev.guerrasOlvidadas.domain.model.Persona;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroDatosJuego(
        @NotBlank String escenario,
        @NotBlank String premios,
        @NotNull Long idUsuario,
        Persona usuario
) {
}
