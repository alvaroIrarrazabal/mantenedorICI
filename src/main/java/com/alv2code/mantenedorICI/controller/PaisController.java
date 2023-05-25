package com.alv2code.mantenedorICI.controller;

import com.alv2code.mantenedorICI.model.Pais;
import com.alv2code.mantenedorICI.response.PaisResponseRest;
import com.alv2code.mantenedorICI.services.IPaisService;

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
public class PaisController {
	
	@Autowired
	private IPaisService service;
	/**
	 * buscar paises
	 * @return
	 */
	
	
	@GetMapping("/paises")
	public ResponseEntity<PaisResponseRest> buscarPaises(){
		ResponseEntity<PaisResponseRest> response = service.buscar();
		return response;
	}
	
	/*
	 * Buscar pais por id
	 */
	
	@GetMapping("/paises/{id}")
	public ResponseEntity<PaisResponseRest> buscarPaisePorId(@PathVariable Long id){
		ResponseEntity<PaisResponseRest> response = service.buscarPorId(id);
		return response;
	}
	
	
	/**
	 * guardar pais
	 * @param pais
	 * @return
	 */
	
	@PostMapping("/paises")
	public ResponseEntity<PaisResponseRest> guardarPais(@RequestBody Pais pais){
		ResponseEntity<PaisResponseRest> response = service.guardar(pais);
		return response;
	}
	
	
	/**
	 * Actualizar pais
	 * @param pais
	 * @param id
	 * @return
	 */
	@PutMapping("/paises/{id}")
	public ResponseEntity<PaisResponseRest> actualizarPais(@RequestBody Pais pais, @PathVariable Long id){
		ResponseEntity<PaisResponseRest> response = service.actualizar(pais, id);
		return response;
	}
	
	
	/**
	 * Eliminar
	 * @param id
	 * @return
	 */
	
	@DeleteMapping("/paises/{id}")
	public ResponseEntity<PaisResponseRest> eliminarPais(@PathVariable Long id){
		ResponseEntity<PaisResponseRest> response = service.eliminar(id);
		return response;
	}
	
	
	
	
	
}
