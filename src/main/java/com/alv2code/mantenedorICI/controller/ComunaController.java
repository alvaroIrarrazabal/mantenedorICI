package com.alv2code.mantenedorICI.controller;

import com.alv2code.mantenedorICI.model.Comuna;
import com.alv2code.mantenedorICI.response.ComunaResponseRest;
import com.alv2code.mantenedorICI.services.IComunaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ComunaController {

	
	@Autowired
	private IComunaService service;
	
	
	
	@GetMapping("/comunas")
	public ResponseEntity<ComunaResponseRest> buscarComunas(){
		
		ResponseEntity<ComunaResponseRest> response = service.buscar();
		return response;
	}
	
	@GetMapping("/comunas/{id}")
	public ResponseEntity<ComunaResponseRest> buscarComunasPorId(@PathVariable long id){
		
		ResponseEntity<ComunaResponseRest> response = service.buscarPorId(id);
		return response;
	}
	
	
	@PostMapping("/comunas")
	public ResponseEntity<ComunaResponseRest> guardarComunas(@RequestBody Comuna comuna){
		
		ResponseEntity<ComunaResponseRest> response = service.guardar(comuna);
		return response;
	}
	
	@PutMapping("/comunas/{id}")
	public ResponseEntity<ComunaResponseRest> actualizarComunas(@RequestBody Comuna comuna, @PathVariable Long id){
		
		ResponseEntity<ComunaResponseRest> response = service.actualizar(comuna, id);
		return response;
	}
	

	@DeleteMapping("/comunas/{id}")
	public ResponseEntity<ComunaResponseRest> borrarComunas( @PathVariable Long id){
		
		ResponseEntity<ComunaResponseRest> response = service.borrar(id);
		return response;
	}
	
}
