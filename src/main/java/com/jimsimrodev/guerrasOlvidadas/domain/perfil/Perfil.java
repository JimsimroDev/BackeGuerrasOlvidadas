package com.jimsimrodev.guerrasOlvidadas.domain.perfil;

import java.util.List;

import com.jimsimrodev.guerrasOlvidadas.domain.persona.Persona;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Perfil")
@Table(name = "perfiles")
public class Perfil {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String rol;
  @OneToMany(mappedBy = "rol")
  private List<Persona> persona;

  public Perfil(RegistrarDatosPerfil registrarDatosPerfil) {
    this.rol = registrarDatosPerfil.rol();
  }
}
