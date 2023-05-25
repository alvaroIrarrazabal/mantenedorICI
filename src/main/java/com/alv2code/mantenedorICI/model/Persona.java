package com.alv2code.mantenedorICI.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.Data;

@Entity
@Table(name="persona")
@Data
public class Persona implements Serializable{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 3919183167791648275L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String email;
	private String telefono;
	private Date   cumple;
	@ManyToOne(fetch=FetchType.EAGER )
	private Pais   pais;
	@ManyToOne(fetch=FetchType.EAGER )
	private Region region;
	@ManyToOne(fetch=FetchType.EAGER )
	private Comuna comuna;
	private int   supervisorId;
	private String nombreSupervisor;
	private String apellidoPaternoSupervisor;
	private String apellidoMaternoSupervisor;
	
	private String genero;
	private String estadoCivil;
	private String redSocial;
	private int    conyugeId;
	private int    padreId;
	private int    madreId; 
	private int    ocupacionId;
	
	
	
	
	 @ManyToMany(fetch=FetchType.EAGER , cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
     private List<Rol> roles;
     
     public void addRol(Rol rol){
         if(this.roles == null){
             this.roles = new ArrayList<>();
         }
         
         this.roles.add(rol);
     }


}
