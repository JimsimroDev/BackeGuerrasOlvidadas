package com.jimsimrodev.guerrasOlvidadas.usecase.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.ActualizarDatosPersona;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.DatosRegistroPersona;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.ListarDatosPersona;
import com.jimsimrodev.guerrasOlvidadas.domain.model.Persona;
import com.jimsimrodev.guerrasOlvidadas.domain.model.perfil.Perfil;
import com.jimsimrodev.guerrasOlvidadas.infra.repository.PerfilRepository;
import com.jimsimrodev.guerrasOlvidadas.infra.repository.PersonaRepository;
import com.jimsimrodev.guerrasOlvidadas.usecase.PasswordEncoderService;

@Service
public class PersonaServiceImpl implements PersonaService {

  private Persona persona;

  private final PersonaRepository personaRepository;

  private final PerfilRepository perfilRepository;

  private final PasswordEncoderService passwordEncoder;

  @Autowired
  public PersonaServiceImpl(
          PersonaRepository personaRepository,
      PerfilRepository perfilRepository,
      PasswordEncoderService passwordEncoder) {
    this.personaRepository = personaRepository;
    this.perfilRepository = perfilRepository;
    this.passwordEncoder = passwordEncoder;

  }

  @Override
  public Page<ListarDatosPersona> getPersona(Pageable paginacion) {
    return personaRepository.findByActivoTrue(paginacion).map(ListarDatosPersona::new);
  }

  @Override
  public void guardarPersona(DatosRegistroPersona datosRegistroPersona) {
    Perfil rol = perfilRepository.getReferenceById(datosRegistroPersona.fk_rol());

    // Encriptar contraseña antes de guadar
    String contrasenaEncriptada = passwordEncoder.encodePassword(datosRegistroPersona.contrasena());

    datosRegistroPersona = new DatosRegistroPersona(
        datosRegistroPersona.nombre1(),
        datosRegistroPersona.nombre2(),
        datosRegistroPersona.apellido2(),
        datosRegistroPersona.apellido2(),
        datosRegistroPersona.movil(),
        datosRegistroPersona.correo(),
        datosRegistroPersona.usuario(),
        contrasenaEncriptada,
        datosRegistroPersona.fk_rol(),
        rol,
        datosRegistroPersona.direccion());
    personaRepository.save(new Persona(datosRegistroPersona));
  }

  @Override
  public void actualizarPersona(ActualizarDatosPersona actualizarDatosPersona) {
    persona = personaRepository.findById(
        actualizarDatosPersona.id())
        .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

    if (actualizarDatosPersona.nombre1() != null) {
      persona.setNombre1(actualizarDatosPersona.nombre1());
    }
    if (actualizarDatosPersona.nombre2() != null) {
      persona.setNombre2(actualizarDatosPersona.nombre2());
    }
    if (actualizarDatosPersona.apellido1() != null) {
      persona.setApellido1(actualizarDatosPersona.apellido1());
    }
    if (actualizarDatosPersona.apellido2() != null) {
      persona.setApellido2(actualizarDatosPersona.apellido2());
    }
    if (actualizarDatosPersona.nombre1() != null) {
      persona.setMovil(actualizarDatosPersona.movil());
    }
    if (actualizarDatosPersona.nombre1() != null) {
      persona.setCorreo(actualizarDatosPersona.correo());
    }
    personaRepository.save(persona);
  }

  @Override
  public void eliminarPersona(Long id) {
    var personaEncontrada = personaRepository.getReferenceById(id);
    personaEncontrada.desativarPersona();
  }

}
