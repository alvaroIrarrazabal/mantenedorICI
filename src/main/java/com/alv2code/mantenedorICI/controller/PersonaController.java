package com.alv2code.mantenedorICI.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import com.alv2code.mantenedorICI.model.Persona;
import com.alv2code.mantenedorICI.response.PersonaResponseRest;
import com.alv2code.mantenedorICI.services.IPersonaService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class PersonaController {
	
	private IPersonaService servicio;
	
	
	public PersonaController(IPersonaService servicio) {
		super();
		this.servicio = servicio;
	}

	@GetMapping("/personas")
	public ResponseEntity<PersonaResponseRest> buscarPersonas(){
		ResponseEntity<PersonaResponseRest> response = servicio.buscarPersonas();
		return response;
	}
	
	
	@GetMapping("/personas/estadoCivil")
	public ResponseEntity<PersonaResponseRest> buscarPersonasPorEstadoCivil(){
		ResponseEntity<PersonaResponseRest> response = servicio.buscarEstadoCivil();
		return response;
	}
	
	@GetMapping("/personas/supervisores")
	public ResponseEntity<PersonaResponseRest> buscarSupervisor(){
		ResponseEntity<PersonaResponseRest> response = servicio.buscarSupervisor();
		return response;
	}
	@GetMapping("/personas/madres")
	public ResponseEntity<PersonaResponseRest> buscarMadres(){
		ResponseEntity<PersonaResponseRest> response = servicio.buscarMadres();
		return response;
	}
	@GetMapping("/personas/padres")
	public ResponseEntity<PersonaResponseRest> buscarPadres(){
		ResponseEntity<PersonaResponseRest> response = servicio.buscarPadres();
		return response;
	}
	
	@GetMapping("/personas/{id}")
	public ResponseEntity<PersonaResponseRest> buscarPersonaPorId(@PathVariable Long id){
		ResponseEntity<PersonaResponseRest> response = servicio.buscarPersonaPorId(id);
		return response;
	}
	
	/**
	 * Guardar persona
	 * @param nombre
	 * @param apellidoPaterno
	 * @param apellidoMaterno
	 * @param email
	 * @param telefono
	 * @param idPais
	 * @param idRegion
	 * @param idComuna
	 * @param supervisorId
	 * @param genero
	 * @param estadoCivil
	 * @param redSocial
	 * @param conyugeId
	 * @param padreId
	 * @param madreId
	 * @param idRol
	 * @param ocupacionId
	 * @return
	 * @throws IOException
	 */
	
	@PostMapping("/personas")
	public ResponseEntity<PersonaResponseRest> buscarPersonaPorId(
			@RequestParam("nombre") String nombre,
			@RequestParam("apellidoPaterno") String apellidoPaterno,
			@RequestParam("apellidoMaterno") String apellidoMaterno,
			@RequestParam("email") String email,
			@RequestParam("telefono") String telefono,
		@RequestParam("cumple") Date cumple,  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateTime,
			@RequestParam("pais") Long idPais,
			@RequestParam("region") Long idRegion,
			@RequestParam("comuna") Long idComuna,
			@RequestParam("supervisorId") int supervisorId,
			@RequestParam("genero") String genero,
			@RequestParam("estadoCivil") String estadoCivil,
			@RequestParam("redSocial") String redSocial,
			@RequestParam("conyugeId") int conyugeId,
			@RequestParam("padreId") int padreId,
			@RequestParam("madreId") int madreId,
			@RequestParam("roles") Long idRol,
			@RequestParam("ocupacionId") int ocupacionId)throws IOException	
	{
		
		Persona persona = new Persona();
		
		
		
		persona.setNombre(nombre);
		persona.setApellidoPaterno(apellidoPaterno);
		persona.setApellidoMaterno(apellidoMaterno);
		persona.setEmail(email);
		persona.setTelefono(telefono);
		persona.setCumple(cumple);
		persona.setSupervisorId(supervisorId);
		persona.setGenero(genero);
		persona.setEstadoCivil(estadoCivil);
		persona.setRedSocial(redSocial);
		persona.setConyugeId(conyugeId);
		persona.setPadreId(padreId);
		persona.setMadreId(madreId);
		persona.setOcupacionId(ocupacionId);
	
		
		
		ResponseEntity<PersonaResponseRest> response = servicio.guardarPersona(persona, idPais, idRegion, idComuna,idRol);
		return response;
	}
	
	/**
	 * atualizar persona
	 * @param nombre
	 * @param apellidoPaterno
	 * @param apellidoMaterno
	 * @param email
	 * @param telefono
	 * @param idPais
	 * @param idRegion
	 * @param idComuna
	 * @param supervisorId
	 * @param genero
	 * @param estadoCivil
	 * @param redSocial
	 * @param conyugeId
	 * @param padreId
	 * @param madreId
	 * @param idRol
	 * @param ocupacionId
	 * @param id
	 * @return
	 * @throws IOException
	 */
	
	@PutMapping("/personas/{id}")
	public ResponseEntity<PersonaResponseRest> actualizarPersona(
			@RequestParam("nombre") String nombre,
			@RequestParam("apellidoPaterno") String apellidoPaterno,
			@RequestParam("apellidoMaterno") String apellidoMaterno,
			@RequestParam("email") String email,
			@RequestParam("telefono") String telefono,
			@RequestParam("cumple") Date cumple,
			@RequestParam("pais") Long idPais,
			@RequestParam("region") Long idRegion,
			@RequestParam("comuna") Long idComuna,
			@RequestParam("supervisorId") int supervisorId,
			@RequestParam("genero") String genero,
			@RequestParam("estadoCivil") String estadoCivil,
			@RequestParam("redSocial") String redSocial,
			@RequestParam("conyugeId") int conyugeId,
			@RequestParam("padreId") int padreId,
			@RequestParam("madreId") int madreId,
			@RequestParam("roles") Long idRol,
			@RequestParam("ocupacionId") int ocupacionId,
			@PathVariable Long id)throws IOException	
	{
		
		Persona persona = new Persona();
		
		
		
		persona.setNombre(nombre);
		persona.setApellidoPaterno(apellidoPaterno);
		persona.setApellidoMaterno(apellidoMaterno);
		persona.setEmail(email);
		persona.setTelefono(telefono);
		persona.setCumple(cumple);
		persona.setSupervisorId(supervisorId);
		persona.setGenero(genero);
		persona.setEstadoCivil(estadoCivil);
		persona.setRedSocial(redSocial);
		persona.setConyugeId(conyugeId);
		persona.setPadreId(padreId);
		persona.setMadreId(madreId);
		persona.setOcupacionId(ocupacionId);
	
		
		
		ResponseEntity<PersonaResponseRest> response = servicio.actualizarPersona(persona, idRol, id);
		return response;
	}
	
	/**
	 * eliminar persona por id
	 * @param id
	 * @return
	 */

	@DeleteMapping("/personas/{id}")
	public ResponseEntity<PersonaResponseRest> eliminarPersona(@PathVariable Long id){
		ResponseEntity<PersonaResponseRest> response = servicio.borrarPorId(id);
		return response;
	}
	
	
	
	

}
