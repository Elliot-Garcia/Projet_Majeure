package com.fighter.model.modelVehicule;

import java.util.Set;

import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.VehiculeDto;

public abstract class AbstractVehicule implements InterfaceVehicule {

	private final String urlFacility = "http://vps.cpe-sn.fr:8081/facility/";
	
	private VehiculeDto vehicule;
	private Set<FacilityDto> facility;
	
	AbstractVehicule() {
		this.vehicule = new VehiculeDto();
		this.facility = request(urlFacility);
		
		RestTemplate restTemplate = new RestTemplate();
		FacilityDto[] result = restTemplate.getForObject(this.urlFacility, FacilityDto[].class);
	}
	
	@Override
	public boolean deplacement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ravitaillement() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void gestionCrew() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public FacilityDto findFacility() {
		int idFacility = this.vehicule.getFacilityRefID();
		
		return null;
	}
	
	abstract public int Score();

}
