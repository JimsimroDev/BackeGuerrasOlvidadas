package com.jimsimrodev.guerrasOlvidadas.domain.persona;

import com.jimsimrodev.guerrasOlvidadas.domain.direccion.DatosDireccion;
import com.jimsimrodev.guerrasOlvidadas.domain.perfil.DatosPerfil;

public record ListarDatosPersona(
    Long id,
    String nombre1,
    String nombre2,
    String apellido1,
    String apellido2,
    String movil,
    String correo,
    DatosDireccion direccion,
    DatosPerfil perfil) {
  public ListarDatosPersona(Persona persona) {
    this(
        persona.getId(), persona.getNombre1(), persona.getNombre2(),
        persona.getApellido1(), persona.getApellido2(),
        persona.getMovil(), persona.getCorreo(),
        new DatosDireccion(
            persona.getDireccion().getCalle(), persona.getDireccion().getDistrito(),
            persona.getDireccion().getCiudad(), persona.getDireccion().getNumero(),
            persona.getDireccion().getComplemento(), persona.getDireccion().getUrbanizacion(),
            persona.getDireccion().getCodigoPostal(), persona.getDireccion().getProvincia()),
        persona.getRol() != null ? new DatosPerfil(persona.getRol().getId(), persona.getRol().getRol()) : null);
  }
}
