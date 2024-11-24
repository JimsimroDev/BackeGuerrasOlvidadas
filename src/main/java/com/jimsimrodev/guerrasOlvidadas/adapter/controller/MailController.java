package com.jimsimrodev.guerrasOlvidadas.adapter.controller;

import com.jimsimrodev.guerrasOlvidadas.usecase.email.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jimsimrodev.guerrasOlvidadas.adapter.dto.email.RegistroDatosEmail;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.ActualizarDatosContrasena;
import com.jimsimrodev.guerrasOlvidadas.domain.model.Persona;
import com.jimsimrodev.guerrasOlvidadas.infra.repository.PersonaRepository;
import com.jimsimrodev.guerrasOlvidadas.usecase.PasswordEncoderService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enviarCorreo")
public class MailController {

  private String asunto = "";

  @Autowired
  private PasswordEncoderService passwordEncoderService;

  @Autowired
  private PersonaRepository personaRepository;

  @Autowired
  private IEmailService emailService;

  // Request para recordación de usuario
  @PostMapping
  public ResponseEntity<?> ricibirPeticionEmail(@RequestBody @Valid RegistroDatosEmail registroDatosEmail) {
    var correo = personaRepository.findByCorreo(registroDatosEmail.destinatario());
    if (correo.isPresent()) {
      Persona persona = correo.get();
      String nombre = persona.getNombre1() + " "
          + persona.getNombre2() + " "
          + persona.getApellido1() + " "
          + persona.getApellido2();

      String usuario = persona.getUsuario();

      asunto = "Recordación de usuario - De tu Hersomo y divertido juego Guerras Olvidadas";

      emailService.recordarUsuario(registroDatosEmail.destinatario(), asunto, nombre, usuario);

      return ResponseEntity.ok("Mensaje enviado");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("El correo no existe o no esta registrado" + registroDatosEmail.destinatario());
    }
  }

  // Request para restablecer contraseña
  @PostMapping("/reestablecer")
  public ResponseEntity<?> reestablecerContrasena(@RequestBody @Valid RegistroDatosEmail registroDatosEmail) {
    var correo = personaRepository.findByCorreo(registroDatosEmail.destinatario());
    asunto = "Reestablecer contraseña - De tu Hersomo y divertido juego Guerras Olvidadas";

    if (correo.isPresent()) {

      Persona persona = correo.get();
      String nombre = persona.getNombre1() + " "
          + persona.getNombre2() + " "
          + persona.getApellido1() + " "
          + persona.getApellido2();

      emailService.enviarLink(registroDatosEmail.destinatario(), asunto, nombre);
      return ResponseEntity.ok("Revisa tu bandeaj de entrada");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("El correo " + registroDatosEmail.destinatario() + " no esta en la base de datos");
  }

  // Request para actualizar la nueva contraseña
  @PutMapping
  @Transactional
  public ResponseEntity<?> actualizarContrasena(
      @RequestBody @Valid ActualizarDatosContrasena actualizarDatosContrasena) {
    var persona = personaRepository.getReferenceById(actualizarDatosContrasena.id());

    var contrasenaEncriptada = passwordEncoderService.encodePassword(actualizarDatosContrasena.contrasena());

    actualizarDatosContrasena = new ActualizarDatosContrasena(
        actualizarDatosContrasena.id(),
        contrasenaEncriptada);

    persona.actualizarContrasena(actualizarDatosContrasena);

    return ResponseEntity.ok("contraseña actualizada");
  }
}
