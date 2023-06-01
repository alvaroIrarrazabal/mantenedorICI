package com.alv2code.mantenedorICI.controller;

import com.alv2code.mantenedorICI.model.Region;
import com.alv2code.mantenedorICI.response.RegionResponseRest;
import com.alv2code.mantenedorICI.services.IRegionService;

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
public class RegionController {
	
	@Autowired
	private IRegionService service;
	
	
	/**
	 * Buscar regiones
	 * @return
	 */
	
	@GetMapping("/regiones")
	public ResponseEntity<RegionResponseRest> buscarRegiones(){
		ResponseEntity<RegionResponseRest> response = service.buscar();
		return response;
	}
	
	/*buscar regiones
	 * 
	 */
	
	
	
	@GetMapping("/regiones/{id}")
	public ResponseEntity<RegionResponseRest> buscarRegionPorId(@PathVariable Long id){
		ResponseEntity<RegionResponseRest> response = service.buscarPorId(id);
		return response;
	}
	
	/*
	 * buscar regiones por id
	 */
	
	
	@PostMapping("/regiones")
	public ResponseEntity<RegionResponseRest> guardarRegion(@RequestBody Region region){
		ResponseEntity<RegionResponseRest> response = service.guardar(region);
		return response;
	}
	
	
	/*
	 * Actualizar region
	 */
	
	@PutMapping("/regiones/{id}")
	public ResponseEntity<RegionResponseRest> actualizarRegion(@RequestBody Region region, @PathVariable Long id){
		ResponseEntity<RegionResponseRest> response = service.actualizar(region, id);
		return response;
	}
	
	
	/**
	 * Eliminar region
	 * @param id
	 * @return
	 */
	
	@DeleteMapping("/regiones/{id}")
	public ResponseEntity<RegionResponseRest> eliminarRegionPorId( @PathVariable Long id){
		ResponseEntity<RegionResponseRest> response = service.eliminar(id);
		return response;
	}


}
