package com.alv2code.mantenedorICI.services;

import com.alv2code.mantenedorICI.model.Persona;
import com.alv2code.mantenedorICI.response.PersonaResponseRest;

import org.springframework.http.ResponseEntity;

public interface IPersonaService {
	
	public ResponseEntity<PersonaResponseRest> buscarPersonas();
	public ResponseEntity<PersonaResponseRest> buscarPersonaPorId(Long id);
	public ResponseEntity<PersonaResponseRest> guardarPersona(Persona persona,Long paisId, Long regionId, Long comunaId,Long rolId);
	public ResponseEntity<PersonaResponseRest> actualizarPersona(Persona persona,Long rolId , Long id);
	public ResponseEntity<PersonaResponseRest> borrarPorId(Long id);

	
//	public ResponseEntity<PersonaResponseRest> buscarPersonasRol();
}
