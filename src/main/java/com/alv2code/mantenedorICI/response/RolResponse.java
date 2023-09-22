package com.alv2code.mantenedorICI.response;

import java.util.List;

import com.alv2code.mantenedorICI.model.Rol;

import lombok.Data;

public class RolResponse {
	
	private List<Rol> rol;

	public List<Rol> getRol() {
		return rol;
	}

	public void setRol(List<Rol> rol) {
		this.rol = rol;
	}
	
	

}
