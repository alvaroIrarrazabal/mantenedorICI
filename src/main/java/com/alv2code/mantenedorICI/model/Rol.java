package com.alv2code.mantenedorICI.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name="rol")

public class Rol  implements Serializable{

	

	
	private static final long serialVersionUID = 8746802628373013207L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

	  @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	   private List<Persona> personas;
       
       
}
