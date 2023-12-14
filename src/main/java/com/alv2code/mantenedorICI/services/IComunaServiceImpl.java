package com.alv2code.mantenedorICI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.alv2code.mantenedorICI.dao.IComunaDao;
import com.alv2code.mantenedorICI.model.Comuna;
import com.alv2code.mantenedorICI.response.ComunaResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IComunaServiceImpl implements IComunaService {
	
	@Autowired
	private IComunaDao comunaDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ComunaResponseRest> buscar() {
		
		ComunaResponseRest response = new ComunaResponseRest();
		try {
			
			List<Comuna> comuna = (List<Comuna>) comunaDao.findAll();	
			response.getComunaResponse().setComuna(comuna);	
			response.setMetadata("Respuesta OK", "00","Respuesta exitosa");
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "00","Error al consultar");
			return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ComunaResponseRest> buscarPorId(Long id) {
		ComunaResponseRest response = new ComunaResponseRest();
		List<Comuna> lista = new ArrayList<>();
		try {
			 Optional<Comuna> buscarComuna = comunaDao.findById(id);
			 if (buscarComuna.isPresent()) {
				lista.add(buscarComuna.get());
				response.getComunaResponse().setComuna(lista);
				response.setMetadata("Respuesta OK", "00","Respuesta exitosa");
				
			}else {
				response.setMetadata("Respuesta NOK", "00","Comuna no encontrada");
				return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.NOT_FOUND);
				
			}
				
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "00","Error al consultar");
			return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional
	public ResponseEntity<ComunaResponseRest> guardar(Comuna comuna) {
		ComunaResponseRest response = new ComunaResponseRest();
		List<Comuna> lista = new ArrayList<>();
		try {
			
			Comuna comunaGuardada = comunaDao.save(comuna);
			
			
			 if (comunaGuardada != null) {
				lista.add(comunaGuardada);
				response.getComunaResponse().setComuna(lista);
				response.setMetadata("Respuesta OK", "00","Comuna guardada");
				
			}else {
				response.setMetadata("Respuesta NOK", "-1","Comuna no guardada");
				return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.BAD_REQUEST);
				
			}
				
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "00","Error al guardar");
			return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional
	public ResponseEntity<ComunaResponseRest> actualizar(Comuna comuna, Long id) {
		ComunaResponseRest response = new ComunaResponseRest();
		List<Comuna> lista = new ArrayList<>();
		try {
			 Optional<Comuna> buscarComuna = comunaDao.findById(id);
			 if (buscarComuna.isPresent()) {
				
				 buscarComuna.get().setNombre(comuna.getNombre());
				 Comuna comunaActualizada = comunaDao.save(buscarComuna.get());
				 if(comunaActualizada != null) {
					 lista.add(comunaActualizada);
					 response.getComunaResponse().setComuna(lista);
					 response.setMetadata("Respuesta OK", "00", "Comuna guardada");
				 }else {
					 response.setMetadata("Respuesta NOK", "-1", "Comuna no guardada");

					 return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.BAD_REQUEST);
				 }
				
			}else {
				response.setMetadata("Respuesta NOK", "00","Comuna no encontrada");
				return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.NOT_FOUND);
				
			}
				
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "00","Error al consultar");
			return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.OK);
	}


	@Override
	@Transactional
	public ResponseEntity<ComunaResponseRest> borrar(Long id) {
		ComunaResponseRest response = new ComunaResponseRest();

		
		try {
			 Optional<Comuna> buscarComuna = comunaDao.findById(id);
			 if (buscarComuna.isPresent()) {
					comunaDao.deleteById(id);
				 response.setMetadata("Respuesta OK", "00","Comuna eliminada");
				
			}else {
				response.setMetadata("Respuesta NOK", "-1","Comuna no encontrada");
				return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.NOT_FOUND);
				
			}
				
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "00","Error al consultar");
			return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<ComunaResponseRest> buscarComunaPorRegion(Long id) {

		ComunaResponseRest response = new ComunaResponseRest();

		try {
			List<Comuna> comuna = comunaDao.findByRegion(id);

			if (comuna != null) {
				 response.getComunaResponse().setComuna(comuna);
				response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
			} else {
				response.setMetadata("Respuesta NOK", "-1", "Comuna no encontrada");
				return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error en la consulta");
			return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ComunaResponseRest>(response, HttpStatus.OK);
	}

}
