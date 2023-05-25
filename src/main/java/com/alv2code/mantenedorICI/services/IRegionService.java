package com.alv2code.mantenedorICI.services;


import com.alv2code.mantenedorICI.model.Region;
import com.alv2code.mantenedorICI.response.RegionResponseRest;

import org.springframework.http.ResponseEntity;

public interface IRegionService {

	public ResponseEntity<RegionResponseRest> buscar();
	public ResponseEntity<RegionResponseRest> buscarPorId(Long id);
	public ResponseEntity<RegionResponseRest> guardar(Region region);
	public ResponseEntity<RegionResponseRest> actualizar(Region region,Long id);
	public ResponseEntity<RegionResponseRest> eliminar(Long id);
}
