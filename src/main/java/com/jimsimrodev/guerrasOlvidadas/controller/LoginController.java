package com.jimsimrodev.guerrasOlvidadas.controller;

import com.jimsimrodev.guerrasOlvidadas.domain.login.AutenticationDatos;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.Persona;
import com.jimsimrodev.guerrasOlvidadas.infra.security.DatosJWTtokens;
import com.jimsimrodev.guerrasOlvidadas.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@PreAuthorize("permitAll()")
public class LoginController {

  @Autowired
  private TokenService tokenService;
  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping
  public ResponseEntity atenticacion(@RequestBody @Valid AutenticationDatos autenticadionDatos) {

    Authentication authoken = new UsernamePasswordAuthenticationToken(autenticadionDatos.usuario(),
        autenticadionDatos.contrasena());
    System.out.println("Usuario: " + autenticadionDatos.usuario());
    System.out.println("Contraseña: " + autenticadionDatos.contrasena());

    var usuario = authenticationManager.authenticate(authoken);
    var JWTtoke = tokenService.generarToken((Persona) usuario.getPrincipal());
    System.out.println("Token extraído: " + JWTtoke);
    return ResponseEntity.ok(new DatosJWTtokens(JWTtoke));
  }
}
