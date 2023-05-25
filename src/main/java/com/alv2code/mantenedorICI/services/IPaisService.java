package com.alv2code.mantenedorICI.services;

import com.alv2code.mantenedorICI.model.Pais;
import com.alv2code.mantenedorICI.response.PaisResponseRest;

import org.springframework.http.ResponseEntity;

public interface IPaisService {

	public ResponseEntity<PaisResponseRest> buscar();
	public ResponseEntity<PaisResponseRest> buscarPorId(Long id);
	public ResponseEntity<PaisResponseRest> guardar(Pais pais);
	public ResponseEntity<PaisResponseRest> actualizar(Pais pais,Long id);
	public ResponseEntity<PaisResponseRest> eliminar(Long id);
	
}
