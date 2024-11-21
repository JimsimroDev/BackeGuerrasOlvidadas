package com.jimsimrodev.guerrasOlvidadas.domain.model.perfil;

import java.util.List;

import com.jimsimrodev.guerrasOlvidadas.adapter.dto.perfil.RegistrarDatosPerfil;
import com.jimsimrodev.guerrasOlvidadas.domain.model.Persona;
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
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @OneToMany(mappedBy = "rol")
    private List<Persona> persona;

    public Perfil(RegistrarDatosPerfil registrarDatosPerfil) {
        this.rol = registrarDatosPerfil.rol();
    }
}
