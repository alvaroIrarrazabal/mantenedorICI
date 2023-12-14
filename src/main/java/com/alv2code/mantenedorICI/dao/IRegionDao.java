package com.alv2code.mantenedorICI.dao;

import java.util.List;

import com.alv2code.mantenedorICI.model.Region;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IRegionDao extends CrudRepository<Region, Long> {
	
	@Query(value="SELECT * FROM ici.region WHERE pais_id = ?1  ",nativeQuery = true)
	public List<Region> findByPais(Long id);

}
