package com.jimsimrodev.guerrasOlvidadas.infra.repository;

import com.jimsimrodev.guerrasOlvidadas.domain.model.perfil.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
