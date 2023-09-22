package com.alv2code.mantenedorICI.response;

import java.util.List;

import com.alv2code.mantenedorICI.model.Ocupacion;

import lombok.Data;

public class OcupacionResponse {

	
	private List<Ocupacion> ocupacion;

	public List<Ocupacion> getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(List<Ocupacion> ocupacion) {
		this.ocupacion = ocupacion;
	}
	
}
