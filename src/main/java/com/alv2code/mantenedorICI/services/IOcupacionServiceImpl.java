package com.alv2code.mantenedorICI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alv2code.mantenedorICI.dao.IOcupacionDao;
import com.alv2code.mantenedorICI.model.Ocupacion;
import com.alv2code.mantenedorICI.response.OcupacionResponseRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IOcupacionServiceImpl implements IOcupacionService {

	
	@Autowired
	private IOcupacionDao ocupacionDao;
	
	@Override
	@Transactional
	public ResponseEntity<OcupacionResponseRest> save(Ocupacion ocupacion) {
		
		OcupacionResponseRest response = new OcupacionResponseRest();
		List<Ocupacion> lista = new ArrayList<>();
		
		try {
			
			Ocupacion nuevaOcupacion = ocupacionDao.save(ocupacion);
			
			if(nuevaOcupacion != null) {
			lista.add(nuevaOcupacion);
			response.getOcupacionResponse().setOcupacion(lista);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se guardo a la persona");
				return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.BAD_REQUEST);

			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error, no se guardo a la persona");
			return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.OK);

	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<OcupacionResponseRest> obtenerOcupaciones() {
		OcupacionResponseRest response = new OcupacionResponseRest();
		
		try {
			
			List<Ocupacion> ocupaciones = (List<Ocupacion>) ocupacionDao.findAll();
			
			if(ocupaciones != null) {
				response.getOcupacionResponse().setOcupacion(ocupaciones);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontraron Ocupaciónes");
				return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.BAD_REQUEST);

			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error, no se encontraron Ocupaciónes");
			return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<OcupacionResponseRest> obtenerOcupacionesPorId(Long id) {
		OcupacionResponseRest response = new OcupacionResponseRest();
		List<Ocupacion> lista = new ArrayList<>();
		
		try {
			
			Optional<Ocupacion> ocupacion = ocupacionDao.findById(id);
			
			if(ocupacion.isPresent()) {
				lista.add(ocupacion.get());
				response.getOcupacionResponse().setOcupacion(lista);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontraró la ocupación");
				return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.BAD_REQUEST);

			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error, no se encontro la ocupación");
			return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<OcupacionResponseRest> actualizarOcupacion(Ocupacion ocupacion, Long id) {
		OcupacionResponseRest response = new OcupacionResponseRest();
		List<Ocupacion> lista = new ArrayList<>();
		
		try {
			
			Optional<Ocupacion> buscarOcupacion = ocupacionDao.findById(id);
			
			if(buscarOcupacion.isPresent()) {
				buscarOcupacion.get().setNombre(ocupacion.getNombre());
				
				Ocupacion actualizarOcupacion = ocupacionDao.save(buscarOcupacion.get());
				if(actualizarOcupacion != null) {
					lista.add(actualizarOcupacion);
					response.getOcupacionResponse().setOcupacion(lista);
					response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
				}else {
					response.setMetadata("Respuesta NOK", "-1", "Ocupación no actualizada");
					
				}
				
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontraró la ocupación");
				return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.BAD_REQUEST);

			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error, no se actualizó la ocupación");
			return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<OcupacionResponseRest> eliminarOcupacion(Long id) {
		OcupacionResponseRest response = new OcupacionResponseRest();
		
		try {
			
			Optional<Ocupacion> buscarOcupacion = ocupacionDao.findById(id);
			
			if(buscarOcupacion.isPresent()) {
				ocupacionDao.deleteById(id);
			
					response.setMetadata("Respuesta OK", "00", "Ocupación borrada con exito");
				
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se encotró la ocupación");
				return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.BAD_REQUEST);

			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error, no se borró la ocupación");
			return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<OcupacionResponseRest>(response,HttpStatus.OK);
	}

}
