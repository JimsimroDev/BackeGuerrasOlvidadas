package com.jimsimrodev.guerrasOlvidadas.adapter.controller;

import com.jimsimrodev.guerrasOlvidadas.domain.model.perfil.Perfil;
import com.jimsimrodev.guerrasOlvidadas.infra.repository.PerfilRepository;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.perfil.RegistrarDatosPerfil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    public ResponseEntity<?> crearNuevoPerfil(@RequestBody @Valid RegistrarDatosPerfil registrarDatosPerfil) {

        registrarDatosPerfil = new RegistrarDatosPerfil(registrarDatosPerfil.rol());

        Perfil rol = perfilRepository.save(new Perfil(registrarDatosPerfil));
        if (rol != null) {
            return ResponseEntity.ok("Nuevo perfil creado con exito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ingresaron datso formulario vacio");
        }
    }
}
