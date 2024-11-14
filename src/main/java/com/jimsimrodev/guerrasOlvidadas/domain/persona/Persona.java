package com.jimsimrodev.guerrasOlvidadas.domain.persona;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jimsimrodev.guerrasOlvidadas.domain.direccion.Direccion;
import com.jimsimrodev.guerrasOlvidadas.domain.perfil.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Persona")
@Table(name = "personas")
public class Persona implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre1;
  private String nombre2;
  private String apellido1;
  private String apellido2;
  private String movil;
  @Column(unique = true)
  private String correo;
  private String usuario;
  private String contrasena;
  private Boolean activo;
  @Embedded
  private Direccion direccion;

  @ManyToOne
  @JoinColumn(name = "fk_rol")
  private Perfil rol;

  public Persona(DatosRegistroPersona datosRegistroPersona) {

    this.activo = true;
    this.nombre1 = datosRegistroPersona.nombre1();
    this.nombre2 = datosRegistroPersona.nombre2();
    this.apellido1 = datosRegistroPersona.apellido1();
    this.apellido2 = datosRegistroPersona.apellido2();
    this.movil = datosRegistroPersona.movil();
    this.correo = datosRegistroPersona.correo();
    this.usuario = datosRegistroPersona.usuario();
    this.contrasena = datosRegistroPersona.contrasena();
    this.rol = datosRegistroPersona.rol();
    this.direccion = new Direccion(datosRegistroPersona.direccion());
  }

  public void actualizarPersona(ActualizarDatosPersona actualizarDatosPersona) {
    if (actualizarDatosPersona.nombre1() != null) {
      this.nombre1 = actualizarDatosPersona.nombre1();
    }
    if (actualizarDatosPersona.nombre2() != null) {
      this.nombre2 = actualizarDatosPersona.nombre2();
    }
    if (actualizarDatosPersona.apellido1() != null) {
      this.apellido1 = actualizarDatosPersona.apellido1();
    }
    if (actualizarDatosPersona.apellido2() != null) {
      this.apellido2 = actualizarDatosPersona.apellido2();
    }
    if (actualizarDatosPersona.nombre1() != null) {
      this.movil = actualizarDatosPersona.movil();
    }
    if (actualizarDatosPersona.nombre1() != null) {
      this.correo = actualizarDatosPersona.correo();
    }
  }

  public void desativarPersona() {
    this.activo = false;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("administrador"));
  }

  @Override
  public String getPassword() {
    return contrasena;
  }

  @Override
  public String getUsername() {
    return usuario;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
