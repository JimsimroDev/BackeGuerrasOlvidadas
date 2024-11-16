package com.jimsimrodev.guerrasOlvidadas.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jimsimrodev.guerrasOlvidadas.domain.persona.Persona;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

@Service
public class TokenService {

  public String generarToken(Persona usuario) {
    try {
      Algorithm algorithm = Algorithm.HMAC256("1234");
      return JWT.create()
          .withIssuer("guerrasOlvidadas")
          .withSubject(usuario.getUsuario())
          .withClaim("id", usuario.getId())
          .withClaim("roles", usuario.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)  // Extraer roles
          .collect(Collectors.toList()))
          .withExpiresAt(fechaExpiracion())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException();
    }
  }

  // Algoritmo para decodificar y validar
  public String getSubject(String token) {
    DecodedJWT verifier = null;
    try {
      Algorithm algorithm = Algorithm.HMAC256("1234");
      verifier = JWT.require(algorithm)
          .withIssuer("guerrasOlvidadas")
          .build()
          .verify(token);
      verifier.getSubject();// Obtiene el 'subject' del token
    } catch (JWTCreationException exception) {
      throw new RuntimeException(" Verifier invalida");
    }
    return verifier.getSubject();
  }

  private Instant fechaExpiracion() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
  }
}
