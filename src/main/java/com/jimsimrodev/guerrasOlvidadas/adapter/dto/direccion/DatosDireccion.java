package com.jimsimrodev.guerrasOlvidadas.adapter.dto.direccion;

import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(
        @NotBlank String calle,
        @NotBlank String distrito,
        @NotBlank String ciudad,
        @NotBlank String numero,
        @NotBlank String complemento,
        @NotBlank String urbanizacion,
        @NotBlank String codigoPostal,
        @NotBlank String provincia) {

}
