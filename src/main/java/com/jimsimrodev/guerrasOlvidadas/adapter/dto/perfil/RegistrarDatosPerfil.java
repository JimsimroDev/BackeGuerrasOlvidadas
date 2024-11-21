package com.jimsimrodev.guerrasOlvidadas.adapter.dto.perfil;

import com.jimsimrodev.guerrasOlvidadas.domain.model.perfil.Rol;
import jakarta.validation.constraints.NotNull;

public record RegistrarDatosPerfil(
        @NotNull Rol rol) {
}
