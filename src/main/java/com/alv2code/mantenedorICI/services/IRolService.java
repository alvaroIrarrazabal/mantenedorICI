package com.alv2code.mantenedorICI.services;


import com.alv2code.mantenedorICI.response.RolResponseRest;

import org.springframework.http.ResponseEntity;

public interface IRolService {
	
	public ResponseEntity<RolResponseRest>buscar();
	public ResponseEntity<RolResponseRest>buscarporId(Long id);
	
	
	

}
