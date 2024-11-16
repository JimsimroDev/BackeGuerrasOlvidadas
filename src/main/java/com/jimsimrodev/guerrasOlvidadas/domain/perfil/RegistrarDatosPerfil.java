package com.jimsimrodev.guerrasOlvidadas.domain.perfil;

import jakarta.validation.constraints.NotNull;

public record RegistrarDatosPerfil(
    @NotNull Rol rol) {
}
