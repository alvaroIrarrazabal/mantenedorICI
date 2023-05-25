package com.alv2code.mantenedorICI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alv2code.mantenedorICI.dao.IRegionDao;
import com.alv2code.mantenedorICI.model.Region;
import com.alv2code.mantenedorICI.response.RegionResponseRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class IRegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionDao regionDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<RegionResponseRest> buscar() {

		RegionResponseRest response = new RegionResponseRest();

		try {
			List<Region> region = (List<Region>) regionDao.findAll();
			response.getRegionResponse().setRegion(region);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");

		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error en la consulta");
			return new ResponseEntity<RegionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<RegionResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<RegionResponseRest> buscarPorId(@PathVariable Long id) {

		RegionResponseRest response = new RegionResponseRest();
		List<Region> lista = new ArrayList<>();

		try {
			Optional<Region> region = regionDao.findById(id);

			if (region.isPresent()) {
				lista.add(region.get());
				response.getRegionResponse().setRegion(lista);
				response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
			} else {
				response.setMetadata("Respuesta NOK", "-1", "Region no encontrada");
				return new ResponseEntity<RegionResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error en la consulta");
			return new ResponseEntity<RegionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<RegionResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<RegionResponseRest> guardar(Region region) {
		RegionResponseRest response = new RegionResponseRest();
		List<Region> lista = new ArrayList<>();

		try {
			Region regionGuardada = regionDao.save(region);
			if (regionGuardada != null) {
				lista.add(regionGuardada);
				response.getRegionResponse().setRegion(lista);
				response.setMetadata("Respuesta OK", "00", "Region Guardada");

			} else {
				response.setMetadata("Respuesta NOK", "-1", "Region no guardada");
				return new ResponseEntity<RegionResponseRest>(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error en la consulta");
			return new ResponseEntity<RegionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<RegionResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<RegionResponseRest> actualizar(Region region, Long id) {
		RegionResponseRest response = new RegionResponseRest();
		List<Region> lista = new ArrayList<>();

		try {
			Optional<Region> buscarregion = regionDao.findById(id);
			if (buscarregion.isPresent()) {

				buscarregion.get().setNombre(region.getNombre());

				Region regionActualizada = regionDao.save(buscarregion.get());

				if (regionActualizada != null) {
					lista.add(regionActualizada);
					response.getRegionResponse().setRegion(lista);
					response.setMetadata("Respuesta OK", "00", "Region Actualizada");
				} else {
					response.setMetadata("Respuesta OK", "00", "Region no Actualizada");
					return new ResponseEntity<RegionResponseRest>(response, HttpStatus.BAD_REQUEST);
				}

			} else {
				response.setMetadata("Respuesta NOK", "-1", "Region no encontrada");
				return new ResponseEntity<RegionResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error en la consulta");
			return new ResponseEntity<RegionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<RegionResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<RegionResponseRest> eliminar(Long id) {
		RegionResponseRest response = new RegionResponseRest();

		try {
			Optional<Region> buscarregion = regionDao.findById(id);
			if (buscarregion.isPresent()) {

				regionDao.deleteById(id);

				response.setMetadata("Respuesta OK", "00", "Region Eliminada");
			} else {
				response.setMetadata("Respuesta NOK", "-1", "Region no encontrada");
				return new ResponseEntity<RegionResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error en la consulta");
			return new ResponseEntity<RegionResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<RegionResponseRest>(response, HttpStatus.OK);
	}

}
