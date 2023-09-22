package com.alv2code.mantenedorICI.response;

import java.util.List;

import com.alv2code.mantenedorICI.model.Region;

import lombok.Data;

public class RegionResponse {
	
	private List<Region> region;

	public List<Region> getRegion() {
		return region;
	}

	public void setRegion(List<Region> region) {
		this.region = region;
	}
	
	
	
	

}
