package com.jimsimrodev.guerrasOlvidadas.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jimsimrodev.guerrasOlvidadas.domain.persona.UserRepository;

/**
 * AutenticationService
 */
@Service
public class AutenticationService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var usuario = userRepository.findByUsuario(username);

    if (usuario == null) {
      throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
    return usuario;
  }

  public UserDetails findByUsuario(String username) {
    System.out.println("Consultando usuario: " + username);
    return findByUsuario(username);
  }

}
