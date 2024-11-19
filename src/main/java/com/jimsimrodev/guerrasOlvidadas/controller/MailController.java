package com.jimsimrodev.guerrasOlvidadas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jimsimrodev.guerrasOlvidadas.domain.email.RegistroDatosEmail;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.ActualizarDatosContrasena;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.Persona;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.PersonaRepository;
import com.jimsimrodev.guerrasOlvidadas.service.IEmailService;
import com.jimsimrodev.guerrasOlvidadas.service.PasswordEncoderService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/enviarCorreo")
public class MailController {

  private String mensaje = "";
  private String asunto = "";

  @Autowired
  private PasswordEncoderService passwordEncoderService;

  @Autowired
  private PersonaRepository personaRepository;

  @Autowired
  private IEmailService emailService;

  @PostMapping
  public ResponseEntity<?> ricibirPeticionEmail(@RequestBody @Valid RegistroDatosEmail registroDatosEmail) {
    var correo = personaRepository.findByCorreo(registroDatosEmail.destinatario());
    if (correo.isPresent()) {
      Persona persona = correo.get();
      String nombre = persona.getNombre1() + " " + persona.getNombre2();
      String apellido = " " + persona.getApellido1() + " " + persona.getApellido2();
      String usuario = persona.getUsuario();
      String contrasena = persona.getContrasena();

      mensaje = "Hola, ¿Cómo estás? " + nombre + apellido +
          "\nAcabamos de recibir tu solicitud y queremos informarte que el usuario para ingresar a Guerra Olvidadas es: "
          + usuario + "\n y tu contraseña es: " + contrasena;
      asunto = "Recordación de usuario y contraseña - De tu Hersomo y divertido juego Guerras Olvidadas";
      emailService.enviarCorro(registroDatosEmail.destinatario(), asunto, mensaje);

      return ResponseEntity.ok("Mensaje enviado");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("El correo no existe o no esta registrado" + registroDatosEmail.destinatario());
    }
  }

  @PostMapping("/reestablecer")
  public ResponseEntity<?> reestablecerContrasena(@RequestBody @Valid RegistroDatosEmail registroDatosEmail) {
    var correo = personaRepository.findByCorreo(registroDatosEmail.destinatario());

    System.out.println(registroDatosEmail.destinatario());
    mensaje = "https://miro.com/app/dashboard/";

    if (correo.isPresent()) {
      emailService.enviarCorro(registroDatosEmail.destinatario(), asunto, mensaje);
      return ResponseEntity.ok("Revisa tu bandeaj de entrada");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("El correo " + registroDatosEmail.destinatario() + " no esta en la base de datos");
  }

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
