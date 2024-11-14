package com.jimsimrodev.guerrasOlvidadas.controller;

import com.jimsimrodev.guerrasOlvidadas.domain.email.RegistroDatosEmail;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.Persona;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.PersonaRepository;
import com.jimsimrodev.guerrasOlvidadas.service.IEmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/enviarCorreo")
public class MailController {

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

      String mensaje = "Hola, ¿Cómo estás? " + nombre + apellido +
          "\nAcabamos de recibir tu solicitud y queremos informarte que el usuario para ingresar a Guerra Olvidadas es: "
          + usuario + "\n y tu contraseña es: " + contrasena;
      String asunto = "Recordación de usuario y contraseña - De tu Hersomo y divertido juego Guerras Olvidadas";
      emailService.enviarCorro(registroDatosEmail.destinatario(), asunto, mensaje);

      return ResponseEntity.ok("Mensaje enviado");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("El correo no existe o no esta registrado" + registroDatosEmail.destinatario());
    }
  }
}
