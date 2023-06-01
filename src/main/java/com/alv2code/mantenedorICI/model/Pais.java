package com.alv2code.mantenedorICI.model;

import java.io.Serializable;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



import lombok.Data;

@Data
@Entity
@Table(name="pais")
public class Pais implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 2526332797337888017L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	


}
