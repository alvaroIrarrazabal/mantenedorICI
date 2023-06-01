package com.alv2code.mantenedorICI.services;


import com.alv2code.mantenedorICI.model.Ocupacion;
import com.alv2code.mantenedorICI.response.OcupacionResponseRest;

import org.springframework.http.ResponseEntity;

public interface IOcupacionService {

	
	public ResponseEntity<OcupacionResponseRest> save(Ocupacion ocupacion);
	public ResponseEntity<OcupacionResponseRest> obtenerOcupaciones();
	public ResponseEntity<OcupacionResponseRest> obtenerOcupacionesPorId(Long id);
	public ResponseEntity<OcupacionResponseRest> actualizarOcupacion(Ocupacion ocupacion,Long id);
	public ResponseEntity<OcupacionResponseRest> eliminarOcupacion(Long id);
}
