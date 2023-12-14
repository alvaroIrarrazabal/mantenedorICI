package com.alv2code.mantenedorICI.dao;

import java.util.List;

import com.alv2code.mantenedorICI.model.Persona;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IPersonaDao extends CrudRepository<Persona, Long> {

	
	@Query(value ="select * from ici.persona  where estado_civil ='casado'",nativeQuery = true)
	public List<Persona> buscarEstadoCivil();
	
	@Query(value="select * from ici.persona p inner join ici.persona_roles r on(p.id = r.personas_id) "
			+ "where r.roles_id=2", nativeQuery = true) 
	public List<Persona> buscarSupervisor();
	
	
	@Query(value="select * from ici.persona p inner join ici.persona_roles r on(p.id = r.personas_id) "
			+ "where r.roles_id=3", nativeQuery = true) 
	public List<Persona> buscarPadres();
	
	@Query(value="select * from ici.persona p inner join ici.persona_roles r on(p.id = r.personas_id) "
			+ "where r.roles_id=4", nativeQuery = true) 
	public List<Persona> buscarMadres();
	
	@Query(value="select *, e.id from ici.persona p inner join ici.estado_civil e on (e.id = p.estado_civil_id) where e.id =2"
			, nativeQuery = true) 
	public List<Persona> buscarConyuge();
	
	
	@Modifying
	@Query(value = "DELETE pr, p\n"
			+ "FROM ici.persona_roles pr\n"
			+ "INNER JOIN ici.persona p ON pr.personas_id = p.id\n"
			+ "WHERE p.id = ?1", nativeQuery = true)
	@Transactional
	void eliminarPersonaYRoles(@Param("personaId") Long personaId);
	
	
	
	
}
