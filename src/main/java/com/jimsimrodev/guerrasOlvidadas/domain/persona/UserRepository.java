package com.jimsimrodev.guerrasOlvidadas.domain.persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Persona, Long> {

  UserDetails findByUsuario(String username);

}
