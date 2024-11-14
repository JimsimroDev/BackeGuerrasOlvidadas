package com.jimsimrodev.guerrasOlvidadas.domain.persona;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
  Page<Persona> findByActivoTrue(Pageable paginacion);

  Optional<Persona> findByCorreo(String correo);

  Optional<Persona> findByUsuario(String usuario);

  Optional<Persona> findByContrasena(String contrasena);
}
