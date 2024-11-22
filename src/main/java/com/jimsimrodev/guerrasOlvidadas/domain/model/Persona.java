package com.jimsimrodev.guerrasOlvidadas.domain.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.ActualizarDatosContrasena;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.ActualizarDatosPersona;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.DatosRegistroPersona;
import com.jimsimrodev.guerrasOlvidadas.domain.model.perfil.Perfil;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Persona")
@Table(name = "personas")
public class Persona implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String nombre1;
  private String nombre2;
  @Column(nullable = false)
  private String apellido1;
  @Column(nullable = false)
  private String apellido2;
  @Column(nullable = false)
  private String movil;
  @Column(unique = true)
  private String correo;
  @Column(unique = true, nullable = false)
  private String usuario;
  @Column(nullable = false)
  private String contrasena;
  private Boolean activo;
  @Embedded
  private Direccion direccion;

  @ManyToOne
  @JoinColumn(name = "fk_rol")
  private Perfil rol;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<HistoriaJuego> historiaJuegos;

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

  public void actualizarContrasena(ActualizarDatosContrasena actualizarDatosContrasena) {
    if (actualizarDatosContrasena.contrasena() != null) {
      this.contrasena = actualizarDatosContrasena.contrasena();
    }
  }

  public void desativarPersona() {
    this.activo = false;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.rol != null && this.rol.getRol() != null) {
      return List.of(new SimpleGrantedAuthority("ROLE_" + this.rol.getRol().name()));
    }
    return List.of();
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
