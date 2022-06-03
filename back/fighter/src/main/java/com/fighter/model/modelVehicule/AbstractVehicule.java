package com.fighter.model.modelVehicule;

import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FacilityDto;
import com.fighter.model.dto.VehiculeDto;

public abstract class AbstractVehicule implements InterfaceVehicule {
	
	protected VehiculeDto vehicule;
	
	AbstractVehicule() {
		this.vehicule = new VehiculeDto();
	}
	
	@Override
	public void deplacement(double lon, double lat) {
		this.vehicule.setLon(lon);
		this.vehicule.setLat(lat);
		Requester.putVehicule(this.vehicule);
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
	public FacilityDto findFacilityById(int id) {
		int idFacility = this.vehicule.getFacilityRefID();
		FacilityDto facility = Requester.requestFacilityByID(idFacility);
		return facility;
	}
	
	abstract public int Score();
	
	@Override
	public VehiculeDto getVehiculeDto() {
		return this.vehicule;
	}

}
