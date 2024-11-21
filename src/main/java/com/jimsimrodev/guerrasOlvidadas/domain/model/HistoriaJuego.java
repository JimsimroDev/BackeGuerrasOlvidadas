package com.jimsimrodev.guerrasOlvidadas.domain.model;

import com.jimsimrodev.guerrasOlvidadas.adapter.dto.historiaJuego.RegistroDatosJuego;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "HistoriaJuego")
@Table(name = "historialJuego")
public class HistoriaJuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String escenario;
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona usuario;
    private String premio;
    private Date fechaHora;

    public HistoriaJuego(RegistroDatosJuego registroDatosJuego) {
        this.escenario = registroDatosJuego.escenario();
        this.usuario = registroDatosJuego.usuario();
        this.premio = registroDatosJuego.premios();
        this.fechaHora = new Date();
    }
}