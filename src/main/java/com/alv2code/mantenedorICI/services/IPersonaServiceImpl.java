package com.alv2code.mantenedorICI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceContext;

import com.alv2code.mantenedorICI.dao.IComunaDao;
import com.alv2code.mantenedorICI.dao.IEstadoCivilDao;
import com.alv2code.mantenedorICI.dao.IPersonaDao;
import com.alv2code.mantenedorICI.dao.IRegionDao;
import com.alv2code.mantenedorICI.dao.IRolDao;
import com.alv2code.mantenedorICI.dao.IpaisDao;
import com.alv2code.mantenedorICI.model.Comuna;
import com.alv2code.mantenedorICI.model.EstadoCivil;
import com.alv2code.mantenedorICI.model.Pais;
import com.alv2code.mantenedorICI.model.Persona;
import com.alv2code.mantenedorICI.model.Region;
import com.alv2code.mantenedorICI.model.Rol;
import com.alv2code.mantenedorICI.response.PersonaResponseRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IPersonaServiceImpl implements IPersonaService{
	
	

	  

	    @Autowired
	    private IPersonaDao personaDao;
	

	@Autowired
	private IpaisDao paisDao;
	@Autowired
	private IRegionDao regionDao;
	@Autowired
	private IComunaDao comunaDao;
	@Autowired
	private IRolDao rolDao;
	@Autowired
	private IEstadoCivilDao estadoDao;
	

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<PersonaResponseRest> buscarPersonas() {
		PersonaResponseRest response = new PersonaResponseRest();
		
		try {
			List<Persona> persona = (List<Persona>) personaDao.findAll(); 
			if(persona.size() > 0) {
				for (Persona persona2 : persona) {
					persona2.getRoles();
				}
			}
			
			response.getPersonaResponse().setPersona(persona);
			response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error al consultar");
			return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<PersonaResponseRest> buscarPersonaPorId(Long id) {
	PersonaResponseRest response = new PersonaResponseRest();
	List<Persona> lista = new ArrayList<>();
		
		try {
			Optional<Persona> persona = personaDao.findById(id);
			if(persona.isPresent()) {
				lista.add(persona.get());
					
				response.getPersonaResponse().setPersona(lista);
				response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
				
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontro la persona");
				return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error al consultar el id");
			return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
	}

	
	
	public ResponseEntity<PersonaResponseRest> guardarPersona(Persona persona,Long paisId, Long regionId, Long comunaId,Long rolId,Long estadoCivilId) {
		PersonaResponseRest response = new PersonaResponseRest();
		List<Persona> lista = new ArrayList<>();
		List<Rol> listaRol = new ArrayList<>();
			
			try {
				Optional<Rol> rol = rolDao.findById(rolId);
				if(rol.isPresent()) {
					listaRol.add(rol.get());
					persona.setRoles(listaRol);
				}else {
					response.setMetadata("Respuesta NOK", "-1", "Rol no encontrado");
					return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
				}
				Optional<Pais> pais = paisDao.findById(paisId);
				if(pais.isPresent()) {
					persona.setPais(pais.get());
				}else {
					response.setMetadata("Respuesta NOK", "-1", "pais no encontrado");
					return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
				}
				Optional<Region> region = regionDao.findById(regionId);
				if(region.isPresent()) {
					persona.setRegion(region.get());
				}else {
					response.setMetadata("Respuesta NOK", "-1", "region no encontrada");
					return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
				}
				Optional<Comuna> comuna = comunaDao.findById(comunaId);
				if(comuna.isPresent()) {
					persona.setComuna(comuna.get());
				}else {
					response.setMetadata("Respuesta NOK", "-1", "comuna no encontrada");
					return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
				}
				Optional<EstadoCivil> estado = estadoDao.findById(estadoCivilId);
				if(estado.isPresent()) {
					persona.setEstadoCivil(estado.get());
				}else {
					response.setMetadata("Respuesta NOK", "-1", "estadoCivil no encontrada");
					return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
				}
				Persona personaGuardada = personaDao.save(persona);
				if(personaGuardada != null) {
						
					lista.add(personaGuardada);
					
					response.getPersonaResponse().setPersona(lista);
					response.setMetadata("Respuesta OK", "00", "Persona Guardada");
					
				}else {
					response.setMetadata("Respuesta NOK", "-1", "persona no guardada");
					return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} catch (Exception e) {
				response.setMetadata("Respuesta NOK", "-1", "Error al guardar la persona");
				e.getMessage();
				return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
	}
	
	
	
	@Override
	@Transactional
	public ResponseEntity<PersonaResponseRest> actualizarPersona(Persona persona,Long id, Long paisId, Long regionId, Long comunaId,Long rolId, Long estadoCivilId) {
		PersonaResponseRest response = new PersonaResponseRest();
		List<Persona> lista = new ArrayList<>();
		List<Rol> listaRol = new ArrayList<>();
		Pais paises = new Pais();
		Region regiones = new Region();
		Comuna comunas = new Comuna();
		EstadoCivil estados = new EstadoCivil();
			
			try {
				//Buscar el rol y actualizar en el objeto persona
				
					Optional<Rol> rol = rolDao.findById(rolId);
					Optional<Pais> pais = paisDao.findById(paisId);
					Optional<Region> region = regionDao.findById(regionId);
					Optional<Comuna> comuna = comunaDao.findById(comunaId);
					Optional<EstadoCivil> estadoCivil = estadoDao.findById(estadoCivilId);

					
					if(comuna.isPresent()) {
						comunas = comuna.get();
						persona.setComuna(comunas);
					}
					
					
					if(region.isPresent()) {
						regiones = region.get();
						persona.setRegion(regiones);
					}
					if(estadoCivil.isPresent()) {
						estados = estadoCivil.get();
						persona.setEstadoCivil(estados);
					}
					
					
					
					if(pais.isPresent()) {
						paises = pais.get();
						persona.setPais(paises);
					}else {
						response.setMetadata("Respuesta NOK", "-1", "Pais no encontrado");
						return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
					}
					
					if(rol.isPresent()) {
						listaRol.add(rol.get());
						persona.setRoles(listaRol);
					}else {
						response.setMetadata("Respuesta NOK", "-1", "Rol no encontrado");
						return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
					}
					
					//buscar persona
				
					Optional<Persona> personaBuscada = personaDao.findById(id);
					
				if(personaBuscada != null) {
					personaBuscada.get().setNombre(persona.getNombre());
					personaBuscada.get().setApellidoPaterno(persona.getApellidoPaterno());
					personaBuscada.get().setApellidoMaterno(persona.getApellidoMaterno());
					personaBuscada.get().setEmail(persona.getEmail());
					personaBuscada.get().setTelefono(persona.getTelefono());
					personaBuscada.get().setCumple(persona.getCumple());
					personaBuscada.get().setPais(persona.getPais());
					personaBuscada.get().setRegion(persona.getRegion());
					personaBuscada.get().setComuna(persona.getComuna());
					personaBuscada.get().setSupervisorId(persona.getSupervisorId());
					personaBuscada.get().setNombreSupervisor(persona.getNombreSupervisor());
					personaBuscada.get().setApellidoPaternoSupervisor(persona.getApellidoPaternoSupervisor());
					//personaBuscada.get().setApellidoMaternoSupervisor(persona.getApellidoMaternoSupervisor());
					personaBuscada.get().setGenero(persona.getGenero());
					personaBuscada.get().setEstadoCivil(persona.getEstadoCivil());
					personaBuscada.get().setRedSocial(persona.getRedSocial());
					personaBuscada.get().setConyugeId(persona.getConyugeId());
					personaBuscada.get().setPadreId(persona.getPadreId());
					personaBuscada.get().setMadreId(persona.getMadreId());
					personaBuscada.get().setOcupacionId(persona.getOcupacionId());
					personaBuscada.get().setRoles((persona.getRoles()));
					
//				lista.add(personaBuscada.get());
					
					Persona personaActualizada = personaDao.save(personaBuscada.get());
					if(personaActualizada !=  null) {
						lista.add(personaActualizada);
						response.getPersonaResponse().setPersona(lista);
						response.setMetadata("Respuesta OK", "00", "Respuesta Exitosa");
					}else {
						response.setMetadata("Respuesta NOK", "-1", "No se guardo la persona");
						return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.BAD_REQUEST);
					}
			
					
				}else {
					response.setMetadata("Respuesta NOK", "-1", "No se encontro la persona");
					return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
				}
				
			} catch (Exception e) {
				response.setMetadata("Respuesta NOK", "-1", "Error al consultar el id");
				return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<PersonaResponseRest> borrarPorId(Long id) {
		PersonaResponseRest response = new PersonaResponseRest();
			
			try {
				Optional<Persona> persona = personaDao.findById(id);
				if(persona.isPresent()) {
					
						personaDao.eliminarPersonaYRoles(id);
					
					response.setMetadata("Respuesta OK", "00", "Persona Eliminada");
					
				}else {
					response.setMetadata("Respuesta NOK", "-1", "No se encontro la persona");
					return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
				}
				
			} catch (Exception e) {
			    e.printStackTrace(); // Agregar esta línea para imprimir la excepción
			    response.setMetadata("Respuesta NOK", "-1", "Error al borrar la persona");
			    return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
	}

	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<PersonaResponseRest> buscarEstadoCivil() {
		PersonaResponseRest response = new PersonaResponseRest();
		
		try {
			List<Persona> persona = (List<Persona>) personaDao.buscarEstadoCivil(); 
			if(persona.size() > 0) {
				response.getPersonaResponse().setPersona(persona);
				response.setMetadata("Respuesta OK", "00", "Consulta exitosa");
				
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontró data");
				return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error al buscar la persona");
			return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<PersonaResponseRest> buscarSupervisor() {
	PersonaResponseRest response = new PersonaResponseRest();
		
		try {
			List<Persona> persona = (List<Persona>) personaDao.buscarSupervisor();
			if(persona.size() > 0) {
				response.getPersonaResponse().setPersona(persona);
				response.setMetadata("Respuesta OK", "00", "Consulta exitosa");
				
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontró data");
				return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error al buscar al supervisor");
			return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PersonaResponseRest> buscarPadres() {
PersonaResponseRest response = new PersonaResponseRest();
		
		try {
			List<Persona> persona = (List<Persona>) personaDao.buscarPadres();
			if(persona.size() > 0) {
				response.getPersonaResponse().setPersona(persona);
				response.setMetadata("Respuesta OK", "00", "Consulta exitosa");
				
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontró data");
				return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error al buscar la data");
			return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PersonaResponseRest> buscarMadres() {
PersonaResponseRest response = new PersonaResponseRest();
		
		try {
			List<Persona> persona = (List<Persona>) personaDao.buscarMadres();
			if(persona.size() > 0) {
				response.getPersonaResponse().setPersona(persona);
				response.setMetadata("Respuesta OK", "00", "Consulta exitosa");
				
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontró data");
				return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error al buscar la data");
			return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PersonaResponseRest> buscarConyuge() {
		PersonaResponseRest response = new PersonaResponseRest();
		
		try {
			List<Persona> persona = (List<Persona>) personaDao.buscarConyuge();
			if(persona.size() > 0) {
				response.getPersonaResponse().setPersona(persona);
				response.setMetadata("Respuesta OK", "00", "Consulta exitosa");
				
			}else {
				response.setMetadata("Respuesta NOK", "-1", "No se encontró data");
				return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			response.setMetadata("Respuesta NOK", "-1", "Error al buscar la data");
			return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<PersonaResponseRest>(response, HttpStatus.OK);
	}
	

}

	


