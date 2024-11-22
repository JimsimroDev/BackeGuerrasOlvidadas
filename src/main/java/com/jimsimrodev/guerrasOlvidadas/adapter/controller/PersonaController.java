package com.jimsimrodev.guerrasOlvidadas.adapter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.ActualizarDatosPersona;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.DatosRegistroPersona;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.ListarDatosPersona;
import com.jimsimrodev.guerrasOlvidadas.domain.model.Persona;
import com.jimsimrodev.guerrasOlvidadas.infra.repository.PerfilRepository;
import com.jimsimrodev.guerrasOlvidadas.infra.repository.PersonaRepository;
import com.jimsimrodev.guerrasOlvidadas.usecase.PasswordEncoderService;
import com.jimsimrodev.guerrasOlvidadas.usecase.persona.PersonaServiceImpl;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaServiceImpl personaServiceImpl;

    @GetMapping
    // @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Page<ListarDatosPersona>> listarPersonas(@PageableDefault(size = 9) Pageable paginacion) {
        return ResponseEntity.ok(personaServiceImpl.getPersona(paginacion));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> guardarPersona(@RequestBody @Valid DatosRegistroPersona datosRegistroPersona) {
        if (datosRegistroPersona.fk_rol() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se asignó un rol");
        }
        try {
            // Guardar la persona usando el servicio
            personaServiceImpl.guardarPersona(datosRegistroPersona);
            return ResponseEntity.ok("Registro Exitoso!");
        } catch (Exception e) {
            // Si ocurre algún error en la lógica de guardado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la persona: " + e.getMessage());
        }
    }

    @PutMapping
    @Transactional
    // @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> actualizarPersona(@RequestBody @Valid ActualizarDatosPersona atualizarDatosPersona) {
        personaServiceImpl.actualizarPersona(atualizarDatosPersona);
        return ResponseEntity.noContent().build();// codigo de respuesta 204
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> elimnarPersona(@PathVariable Long id) {
        personaServiceImpl.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }
}
