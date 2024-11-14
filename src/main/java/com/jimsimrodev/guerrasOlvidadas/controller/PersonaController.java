package com.jimsimrodev.guerrasOlvidadas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jimsimrodev.guerrasOlvidadas.domain.perfil.Perfil;
import com.jimsimrodev.guerrasOlvidadas.domain.perfil.PerfilRepository;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.ActualizarDatosPersona;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.DatosRegistroPersona;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.ListarDatosPersona;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.Persona;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.PersonaRepository;
import com.jimsimrodev.guerrasOlvidadas.service.PasswordEncoderService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/personas")
public class PersonaController {

  @Autowired
  private PersonaRepository personaRepository;

  @Autowired
  private PerfilRepository perRepository;

  @Autowired
  private PasswordEncoderService passwordEncoder;

  @GetMapping
  public ResponseEntity<Page<ListarDatosPersona>> listarPersonas(@PageableDefault(size = 9) Pageable paginacion) {
    return ResponseEntity.ok(personaRepository.findByActivoTrue(paginacion).map(ListarDatosPersona::new));
  }

  @PostMapping
  @Transactional
  public ResponseEntity<?> guardarPersona(@RequestBody @Valid DatosRegistroPersona datosRegistroPersona) {
    System.out.println(datosRegistroPersona.fk_rol());
    Perfil rol = perRepository.getReferenceById(datosRegistroPersona.fk_rol());

    // Encriptar contraseña antes de guadar
    String contrasenaEncriptada = passwordEncoder.encodePassword(datosRegistroPersona.contrasena());

    datosRegistroPersona = new DatosRegistroPersona(
        datosRegistroPersona.nombre1(),
        datosRegistroPersona.nombre2(),
        datosRegistroPersona.apellido2(),
        datosRegistroPersona.apellido2(),
        datosRegistroPersona.movil(),
        datosRegistroPersona.correo(),
        datosRegistroPersona.usuario(),
        contrasenaEncriptada,
        datosRegistroPersona.fk_rol(),
        rol,
        datosRegistroPersona.direccion());

    if (datosRegistroPersona.fk_rol() == null) {
      ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se asigno un rol");
    }

    Persona persona = personaRepository.save(new Persona(datosRegistroPersona));
    if (persona != null) {
      return ResponseEntity.ok("Registro Exitoso!");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encutran datos");
    }
  }

  @PutMapping
  @Transactional
  public ResponseEntity<?> actualizarPersona(@RequestBody @Valid ActualizarDatosPersona atualizarDatosPersona) {
    Persona persona = personaRepository.getReferenceById(atualizarDatosPersona.id());
    persona.actualizarPersona(atualizarDatosPersona);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> elimnarPersona(@PathVariable Long id) {
    Persona persona = personaRepository.getReferenceById(id);
    persona.desativarPersona();
    return ResponseEntity.ok().build();
  }
  /*
   * Elimina de la base de datos permanentemente
   * public ResponseEntity elimnarPersona(@PathVariable Long id){
   * Persona persona = personaRepository.getReferenceById(id);
   * personaRepository.delete(persona);
   * return ResponseEntity.ok().build();
   * }
   */
}
