package com.jimsimrodev.guerrasOlvidadas.adapter.controller;

import com.jimsimrodev.guerrasOlvidadas.domain.model.HistoriaJuego;
import com.jimsimrodev.guerrasOlvidadas.infra.repository.HistoriaJuegoRepository;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.historiaJuego.RegistroDatosJuego;
import com.jimsimrodev.guerrasOlvidadas.infra.repository.PersonaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historialJuego")
public class HistoriaJuegoController {

    @Autowired
    private HistoriaJuegoRepository historiaJuegoRepository;

    @Autowired
    PersonaRepository personaRepository;

    @PostMapping
    public ResponseEntity<?> mostrarHistorialJuego(@RequestBody @Valid RegistroDatosJuego registroDatosJuego) {
        System.out.println("este es el id %d " + registroDatosJuego);
        var usuario = personaRepository.getReferenceById(registroDatosJuego.idUsuario());

        registroDatosJuego = new RegistroDatosJuego(
                registroDatosJuego.escenario(),
                registroDatosJuego.premios(),
                registroDatosJuego.idUsuario(),
                usuario
        );

        HistoriaJuego historial = historiaJuegoRepository.save(new HistoriaJuego(registroDatosJuego));
        if (historial != null) {
            return ResponseEntity.ok().body("Registo exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran datos ");
        }
    }
}
