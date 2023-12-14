package com.alv2code.mantenedorICI.services;

import com.alv2code.mantenedorICI.model.Persona;
import com.alv2code.mantenedorICI.response.PersonaResponseRest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface IPersonaService {
	
	public ResponseEntity<PersonaResponseRest> buscarPersonas();
	public ResponseEntity<PersonaResponseRest> buscarPersonaPorId(Long id);
	public ResponseEntity<PersonaResponseRest> guardarPersona(Persona persona,Long paisId, Long regionId, Long comunaId,Long rolId, Long estadoId);
	public ResponseEntity<PersonaResponseRest> actualizarPersona(Persona persona,Long id,Long paisId, Long regionId, Long comunaId,Long rolId,Long estadoId);
	public ResponseEntity<PersonaResponseRest> borrarPorId(Long id);
	public ResponseEntity<PersonaResponseRest> buscarSupervisor();
	public ResponseEntity<PersonaResponseRest> buscarEstadoCivil();
	public ResponseEntity<PersonaResponseRest> buscarPadres();
	public ResponseEntity<PersonaResponseRest> buscarMadres();
	public ResponseEntity<PersonaResponseRest> buscarConyuge();
	
//	public ResponseEntity<PersonaResponseRest> buscarPersonasRol();
}
