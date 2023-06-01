package com.alv2code.mantenedorICI.controller;

import com.alv2code.mantenedorICI.model.Ocupacion;
import com.alv2code.mantenedorICI.response.OcupacionResponseRest;
import com.alv2code.mantenedorICI.services.IOcupacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class OcupacionController {
	
	@Autowired
	private IOcupacionService service;
	
	@PostMapping("/ocupaciones")
	public ResponseEntity<OcupacionResponseRest> guardar(@RequestBody Ocupacion ocupacion){
		ResponseEntity<OcupacionResponseRest> response = service.save(ocupacion);
		return response;
		
	}
	
	@GetMapping("/ocupaciones")
	public ResponseEntity<OcupacionResponseRest> buscarOcupaciones(){
		ResponseEntity<OcupacionResponseRest> response = service.obtenerOcupaciones();
		return response;
		
	}
	
	@GetMapping("/ocupaciones/{id}")
	public ResponseEntity<OcupacionResponseRest> buscarOcupaciones(@PathVariable Long id){
		ResponseEntity<OcupacionResponseRest> response = service.obtenerOcupacionesPorId(id);
		return response;
		
	}
	
	@PutMapping("/ocupaciones/{id}")
	public ResponseEntity<OcupacionResponseRest> actualizarOcupacion(@RequestBody Ocupacion ocupacion,@PathVariable Long id){
		ResponseEntity<OcupacionResponseRest> response = service.actualizarOcupacion(ocupacion, id);
		return response;
		
	}
	
	@DeleteMapping("/ocupaciones/{id}")
	public ResponseEntity<OcupacionResponseRest> eliminarOcupacion(@PathVariable Long id){
		ResponseEntity<OcupacionResponseRest> response = service.eliminarOcupacion(id);
		return response;
		
	}
	

}
