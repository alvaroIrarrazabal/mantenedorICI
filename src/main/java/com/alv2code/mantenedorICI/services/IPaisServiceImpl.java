package com.alv2code.mantenedorICI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alv2code.mantenedorICI.dao.IpaisDao;
import com.alv2code.mantenedorICI.model.Pais;
import com.alv2code.mantenedorICI.response.PaisResponseRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IPaisServiceImpl implements IPaisService {

	@Autowired
	private IpaisDao paisDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<PaisResponseRest> buscar() {

		PaisResponseRest response = new PaisResponseRest();

		try {
			List<Pais> pais = (List<Pais>) paisDao.findAll();
			response.getPaisResponse().setPais(pais);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
		} catch (Exception e) {

			response.setMetadata("Respuesta NOK", "-1", "Error al consultar");
			return new ResponseEntity<PaisResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<PaisResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PaisResponseRest> buscarPorId(Long id) {
		PaisResponseRest response = new PaisResponseRest();
		List<Pais> list = new ArrayList<>();
		try {
			Optional<Pais> pais = paisDao.findById(id);
			if (pais.isPresent()) {
				list.add(pais.get());
				response.getPaisResponse().setPais(list);
				response.setMetadata("Respuesta OK", "00", "Pais encontrado");

			} else {
				response.setMetadata("Respuesta NOK", "-1", "Error al consultar por id");
				return new ResponseEntity<PaisResponseRest>(response, HttpStatus.NOT_FOUND);

			}

		} catch (Exception e) {

			response.setMetadata("Respuesta NOK", "-1", "No se encontro el pais");
			return new ResponseEntity<PaisResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<PaisResponseRest>(response, HttpStatus.OK);

	}

	@Override
	@Transactional
	public ResponseEntity<PaisResponseRest> guardar(Pais pais) {
		PaisResponseRest response = new PaisResponseRest();
		List<Pais> list = new ArrayList<>();
		try {

			Pais paisGuardado = paisDao.save(pais);
			if(paisGuardado!= null) {
				list.add(paisGuardado);
				response.getPaisResponse().setPais(list);
				response.setMetadata("Respuesta OK", "00", "Pais guardado");
			}else {
				response.setMetadata("Respuesta NOK", "-1", "Pais no guardado");
				return new ResponseEntity<PaisResponseRest>(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {

			response.setMetadata("Respuesta NOK", "-1", "Error al grabar pais");
			return new ResponseEntity<PaisResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<PaisResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<PaisResponseRest> actualizar(Pais pais, Long id) {
		PaisResponseRest response = new PaisResponseRest();
		List<Pais> list = new ArrayList<>();
		try {

			Optional<Pais> buscarPais = paisDao.findById(id);
			if(buscarPais.isPresent()) {
				
				buscarPais.get().setNombre(pais.getNombre());
				
				Pais paisActualizado = paisDao.save(buscarPais.get());
				
				if(paisActualizado!=null) {
					list.add(paisActualizado);
					response.getPaisResponse().setPais(list);
					response.setMetadata("Respuesta OK", "00", "Pais Actualizado");
				}else {
					response.setMetadata("Respuesta NOK", "-1", "Pais no actualizado");
					return new ResponseEntity<PaisResponseRest>(response, HttpStatus.BAD_REQUEST);
				}

			}else {
				response.setMetadata("Respuesta NOK", "-1", "Pais no encontrado");
				return new ResponseEntity<PaisResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {

			response.setMetadata("Respuesta NOK", "-1", "Error al actualizar pais");
			e.getStackTrace();
			return new ResponseEntity<PaisResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<PaisResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<PaisResponseRest> eliminar(Long id) {
		PaisResponseRest response = new PaisResponseRest();
		try {
			
			Optional<Pais> buscarPais = paisDao.findById(id);
			if(buscarPais.isPresent()) {
				
				paisDao.deleteById(id);
				response.setMetadata("Respuesta NO", "00", "Pais eliminado");

	
			}else {
				response.setMetadata("Respuesta NOK", "-1", "Pais no encontrado");
				return new ResponseEntity<PaisResponseRest>(response, HttpStatus.NOT_FOUND);
			}



		} catch (Exception e) {

			response.setMetadata("Respuesta NOK", "-1", "Error al grabar pais");
			return new ResponseEntity<PaisResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<PaisResponseRest>(response, HttpStatus.OK);
	}

}
