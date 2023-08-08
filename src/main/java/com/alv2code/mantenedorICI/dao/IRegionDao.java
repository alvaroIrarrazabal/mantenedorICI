package com.alv2code.mantenedorICI.dao;

import java.util.List;

import com.alv2code.mantenedorICI.model.Region;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IRegionDao extends CrudRepository<Region, Long> {
	
	@Query("SELECT r FROM Region r WHERE r.pais.id = ?1 ")
	public List<Region> findByPais(Long id);

}
