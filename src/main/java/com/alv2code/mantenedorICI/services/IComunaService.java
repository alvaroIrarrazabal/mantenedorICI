package com.alv2code.mantenedorICI.services;

import com.alv2code.mantenedorICI.model.Comuna;
import com.alv2code.mantenedorICI.response.ComunaResponseRest;

import org.springframework.http.ResponseEntity;

public interface IComunaService {

	
	public ResponseEntity<ComunaResponseRest> buscar();
	public ResponseEntity<ComunaResponseRest> buscarPorId(Long id);
	public ResponseEntity<ComunaResponseRest> guardar(Comuna comuna);
	public ResponseEntity<ComunaResponseRest> actualizar(Comuna comuna, Long id);
	public ResponseEntity<ComunaResponseRest> borrar(Long id);
}
