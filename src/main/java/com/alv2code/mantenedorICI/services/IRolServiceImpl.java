package com.alv2code.mantenedorICI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alv2code.mantenedorICI.dao.IPersonaDao;
import com.alv2code.mantenedorICI.dao.IRolDao;
import com.alv2code.mantenedorICI.model.Rol;
import com.alv2code.mantenedorICI.response.RolResponseRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IRolServiceImpl implements IRolService {

	@Autowired
	public IRolDao rolDao;
	@Autowired
	public IPersonaDao personaDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<RolResponseRest> buscar() {

		RolResponseRest response = new RolResponseRest();

		try {

			List<Rol> rol = (List<Rol>) rolDao.findAll();

			if (rol != null) {
				response.getRolResponse().setRol(rol);
				response.setMetadata("Respuesta OK", "00", "Respuesta existosa");
			} else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontraron Roles");

				return new ResponseEntity<RolResponseRest>(response, HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error al consultar los roles");

			return new ResponseEntity<RolResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<RolResponseRest> buscarporId(Long id) {
		RolResponseRest response = new RolResponseRest();
		List<Rol> lista = new ArrayList<>();

		try {

			Optional<Rol> rol = rolDao.findById(id);
			if (rol.isPresent()) {
				lista.add(rol.get());
				response.getRolResponse().setRol(lista);
				response.setMetadata("Respuesta OK", "00", "Respuesta existosa");

			} else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontraron Roles");

				return new ResponseEntity<RolResponseRest>(response, HttpStatus.NOT_FOUND);

			}
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error al consultar los roles");

			return new ResponseEntity<RolResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<RolResponseRest>(response, HttpStatus.OK);
	}

}
