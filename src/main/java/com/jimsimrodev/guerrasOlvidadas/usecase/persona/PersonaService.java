package com.jimsimrodev.guerrasOlvidadas.usecase.persona;

import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.ActualizarDatosPersona;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.DatosRegistroPersona;
import com.jimsimrodev.guerrasOlvidadas.adapter.dto.persona.ListarDatosPersona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonaService {

  Page<ListarDatosPersona> getPersona(Pageable paginacion);

  void guardarPersona(DatosRegistroPersona datosRegistroPersona);

  void actualizarPersona(ActualizarDatosPersona actualizarDatosPersona);

  void eliminarPersona(Long id);
}
