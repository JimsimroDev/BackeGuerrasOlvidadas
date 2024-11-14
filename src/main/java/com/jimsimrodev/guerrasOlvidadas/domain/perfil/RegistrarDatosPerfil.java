package com.jimsimrodev.guerrasOlvidadas.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record RegistrarDatosPerfil(@NotBlank String rol) {
}
