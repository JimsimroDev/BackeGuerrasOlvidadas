package com.jimsimrodev.guerrasOlvidadas.infra.repository;

import com.jimsimrodev.guerrasOlvidadas.domain.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Persona, Long> {

    UserDetails findByUsuario(String username);

}
