package com.jimsimrodev.guerrasOlvidadas.infra.repository;

import com.jimsimrodev.guerrasOlvidadas.domain.model.HistoriaJuego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriaJuegoRepository extends JpaRepository<HistoriaJuego, Long> {
}
