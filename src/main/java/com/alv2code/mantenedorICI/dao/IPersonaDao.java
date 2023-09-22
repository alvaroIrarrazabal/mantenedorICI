package com.alv2code.mantenedorICI.dao;

import java.util.List;

import com.alv2code.mantenedorICI.model.Persona;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IPersonaDao extends CrudRepository<Persona, Long> {

	
	@Query(value ="select * from ici.persona  where estado_civil ='casado'",nativeQuery = true)
	public List<Persona> buscarEstadoCivil();
	
	@Query(value="select * from ici.persona p inner join persona_roles r on(p.id = r.persona_id) "
			+ "where r.roles_id=2", nativeQuery = true) 
	public List<Persona> buscarSupervisor();
	
	
	@Query(value="select * from ici.persona p inner join ici.persona_roles r on(p.id = r.persona_id) "
			+ "where r.roles_id=4", nativeQuery = true) 
	public List<Persona> buscarPadres();
	
	@Query(value="select * from ici.persona p inner join ici.persona_roles r on(p.id = r.persona_id) "
			+ "where r.roles_id=3", nativeQuery = true) 
	public List<Persona> buscarMadres();
	
	@Query(value="select * from ici.persona p where estado_civil = 'casado'"
			, nativeQuery = true) 
	public List<Persona> buscarConyuge();
	
	
	
}
