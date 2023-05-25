package com.alv2code.mantenedorICI.controller;

import com.alv2code.mantenedorICI.model.Rol;
import com.alv2code.mantenedorICI.response.RolResponseRest;
import com.alv2code.mantenedorICI.services.IRolService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RolController {
	
	@Autowired
	public IRolService service;
	
	
	/**
	 * Buscar todos los roles
	 * @return
	 */
	@GetMapping("/roles")
	public ResponseEntity<RolResponseRest> buscarRoles(){
		
		ResponseEntity<RolResponseRest> response = service.buscar();
		return response;
		
	}
	
	
	/**
	 * Buscar rol por el id
	 * @param id
	 * @return
	 */
	@GetMapping("/roles/{id}")
	public ResponseEntity<RolResponseRest> buscarRolPorId(@PathVariable Long id){
		
		ResponseEntity<RolResponseRest> response = service.buscarporId(id);
		return response;
		
	}
	
	/**
	 * Guardar rol
	 * @param rol
	 * @return
	 */
	@PostMapping("/roles")
	public ResponseEntity<RolResponseRest> guardarRol(
			@RequestParam("nombre")String nombre,
			@RequestParam("idPersona")Long idPersona
			
			){
		Rol rol = new Rol();
		rol.setNombre(nombre);
		
		ResponseEntity<RolResponseRest> response = service.guardar(rol, idPersona);
		return response;
		
	}

}
