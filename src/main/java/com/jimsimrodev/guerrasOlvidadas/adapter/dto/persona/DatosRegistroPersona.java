package com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona;

import com.jimsimrodev.guerrasOlvidadas.adapter.dto.direccion.DatosDireccion;
import com.jimsimrodev.guerrasOlvidadas.domain.model.perfil.Perfil;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroPersona(
    String nombre1,
    String nombre2,
    String apellido1,
    String apellido2,
    String movil,
    @NotBlank @Email String correo,
    String usuario,
    String contrasena,
    @NotNull Long fk_rol,
    Perfil rol,
    @NotNull @Valid DatosDireccion direccion) {
}
